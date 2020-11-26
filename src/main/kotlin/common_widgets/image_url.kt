package common_widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    progressIndicatorColor: Color = dark,
) {
    val (imageAsset, setImageAsset) = remember { mutableStateOf<ImageAsset?>(null) }
    val (oldUrl, setOldUrl) = remember { mutableStateOf(url) }
    val scope = CoroutineScope(Dispatchers.Main)
    var bis: BufferedInputStream? = null
    onCommit {
        if (oldUrl != url) {
            setImageAsset(null)
            setOldUrl(url)
        }
    }
    onDispose {
        scope.cancel()
        bis?.close()
        bis = null
    }
    if (imageAsset == null) {
        when {
            url.isValidURL() -> {
                scope.launch {
                    bis = BufferedInputStream(URL(url).openStream())
                    val asset = withContext(Dispatchers.IO) {
                        Image.makeFromEncoded(bis?.readAllBytes()).asImageAsset()
                    }
                    setImageAsset(asset)
                }
            }
            url.isValidPathToFile() -> {
                scope.launch {
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
                contentScale = contentScale
            )
        }
    }
}