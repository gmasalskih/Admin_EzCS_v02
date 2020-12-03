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

private class ImageLoader {
    private val errAsset = externalImageResource("src/main/resources/icons/icon_err.png")

    @Suppress("BlockingMethodInNonBlockingContext")
    suspend fun loadImage(url: String) = when {
        url.isValidURL() -> {

            try {
                withContext(Dispatchers.IO) {
                    URL(url).openStream().use { inputStream ->
                        BufferedInputStream(inputStream).use { bufferedInputStream ->
                            println("ImageLoader")
                            Image.makeFromEncoded(bufferedInputStream.readAllBytes()).asImageAsset()
                        }
                    }
                }
            } catch (e: Exception) {
                println("ImageLoader - ${e.message}")
                errAsset
            }
        }
        url.isValidPathToFile() -> {
            try {
                withContext(Dispatchers.IO) { externalImageResource(url) }
            } catch (e: Exception) {
                println("ImageLoader - ${e.message}")
                errAsset
            }
        }
        else -> {
            println("ImageLoader - url is:$url")
            errAsset
        }
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
    val (imageAsset, setImageAsset) = remember(url) { mutableStateOf<ImageAsset?>(null) }
//    val (oldUrl, setOldUrl) = remember { mutableStateOf(url) }
    val scope = rememberCoroutineScope()
    var job by remember { mutableStateOf<Job?>(null) }
    onCommit {
//        if (oldUrl != url) {
//            setImageAsset(null)
//            setOldUrl(url)
//        }
    }
    onDispose {
        job?.cancel()
        job = null
    }
    if (imageAsset == null) {
        job?.cancel()
        job = scope.launch {
            setImageAsset(ImageLoader().loadImage(url))
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