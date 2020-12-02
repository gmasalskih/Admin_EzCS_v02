package common_widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageAsset
import androidx.compose.ui.graphics.asImageAsset
import androidx.compose.ui.layout.ContentScale
import kotlinx.coroutines.*
import org.jetbrains.skija.Image
import ui.dark
import utils.externalImageResource
import utils.isValidPathToFile
import utils.isValidURL
import java.io.BufferedInputStream
import java.net.URL

@Composable
fun ImageUrl(
    url: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit,
    colorFilter: ColorFilter? = null,
    progressIndicatorColor: Color = dark,
) {
    val (imageAsset, setImageAsset) = remember { mutableStateOf<ImageAsset?>(null) }
    val (oldUrl, setOldUrl) = remember { mutableStateOf(url) }
    val scope = rememberCoroutineScope()
    var job by remember { mutableStateOf<Job?>(null) }
    onCommit {
        if (oldUrl != url) {
            setImageAsset(null)
            setOldUrl(url)
        }
    }
    onDispose { job?.cancel() }
    if (imageAsset == null) {
        when {
            url.isValidURL() -> {
                job?.cancel()
                job = scope.launch {
                    val asset = withContext(Dispatchers.IO) {
                        Image.makeFromEncoded(BufferedInputStream(URL(url).openStream()).readAllBytes()).asImageAsset()
                    }
                    setImageAsset(asset)
                }

            }
            url.isValidPathToFile() -> {
                job?.cancel()
                job = scope.launch {
                    val asset = withContext(Dispatchers.IO) { externalImageResource(url) }
                    setImageAsset(asset)
                }
            }
        }
    }
    Box(
        modifier = Modifier.then(modifier)
    ) {
        if (imageAsset == null) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = progressIndicatorColor
            )
        } else {
            Image(
                modifier = Modifier.then(modifier),
                asset = imageAsset,
                contentScale = contentScale,
                colorFilter = colorFilter
            )
        }
    }
}