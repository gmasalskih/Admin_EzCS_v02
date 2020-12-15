package common_widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import kotlinx.coroutines.*
import org.jetbrains.skija.Image
import ui.dark
import utils.externalImageResource
import utils.isValidPathToFile
import utils.isValidURL
import java.io.BufferedInputStream
import java.net.URL

@Suppress("BlockingMethodInNonBlockingContext")
private suspend fun loadImage(url: String) = when {
    url.isValidURL() -> {
        try {
            withContext(Dispatchers.IO) {
                URL(url).openStream().use { inputStream ->
                    BufferedInputStream(inputStream).use { bufferedInputStream ->
                        Image.makeFromEncoded(bufferedInputStream.readAllBytes()).asImageBitmap()
                    }
                }
            }
        } catch (e: Exception) {
            println("ImageLoader - ${e.message}")
            externalImageResource("src/main/resources/icons/icon_err.png")
        }
    }
    url.isValidPathToFile() -> {
        try {
            withContext(Dispatchers.IO) { externalImageResource(url) }
        } catch (e: Exception) {
            println("ImageLoader - ${e.message}")
            externalImageResource("src/main/resources/icons/icon_err.png")
        }
    }
    else -> {
        println("ImageLoader - url is:$url")
        externalImageResource("src/main/resources/icons/icon_err.png")
    }
}

@Composable
fun ImageUrl(
    url: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit,
    colorFilter: ColorFilter? = null,
    progressIndicatorColor: Color = dark,
) {
    val (imageAsset, setImageAsset) = remember { mutableStateOf<ImageBitmap?>(null) }
    val (oldUrl, setOldUrl) = remember { mutableStateOf(url) }
    val scope = rememberCoroutineScope()
    var job by remember { mutableStateOf<Job?>(null) }
    onCommit {
        if (oldUrl != url) {
            setImageAsset(null)
            setOldUrl(url)
        }
    }
    onDispose {
        job?.cancel()
        job = null
    }
    if (imageAsset == null) {
        job = scope.launch {
            setImageAsset(loadImage(url))
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
                bitmap = imageAsset,
                contentScale = contentScale,
                colorFilter = colorFilter
            )
        }
    }
}