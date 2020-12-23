package common_widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import data.types.ContentSourceType
import kotlinx.coroutines.*
import org.jetbrains.skija.Image
import org.koin.core.KoinComponent
import org.koin.core.inject
import providers.ContentStorage
import ui.dark
import utils.externalImageResource
import utils.isPathToLocalFileValid
import utils.isValidURL
import java.io.BufferedInputStream
import java.net.URL

object ImageLoader : KoinComponent {
    private val dropbox by inject<ContentStorage>()

    @Suppress("BlockingMethodInNonBlockingContext")
    private suspend fun loadImage(contentSourceType: ContentSourceType): ImageBitmap? = when (contentSourceType) {
        is ContentSourceType.ContentStorageThumbnail -> {
            dropbox.getFileThumbnail(contentSourceType.pathToFolder, contentSourceType.fileName)?.let { byteArray ->
                Image.makeFromEncoded(byteArray).asImageBitmap()
            }
        }
        is ContentSourceType.ContentStorageOriginal -> {
            dropbox.getFile(contentSourceType.pathToFolder, contentSourceType.fileName)?.let { byteArray ->
                Image.makeFromEncoded(byteArray).asImageBitmap()
            }
        }
        is ContentSourceType.URL -> {
            withContext(Dispatchers.IO) {
                URL(contentSourceType.url).openStream().use { inputStream ->
                    BufferedInputStream(inputStream).use { bufferedInputStream ->
                        bufferedInputStream.readAllBytes()?.let { byteArray ->
                            Image.makeFromEncoded(byteArray).asImageBitmap()
                        }
                    }
                }
            }
        }
        is ContentSourceType.File -> {
            withContext(Dispatchers.IO) {
                externalImageResource(contentSourceType.pathToFile)
            }
        }
        is ContentSourceType.Resource -> {
            withContext(Dispatchers.IO) {
                externalImageResource("src/main/resources/icons/icon_err.png")
            }
        }
        is ContentSourceType.Empty -> {
            withContext(Dispatchers.IO) {
                externalImageResource("src/main/resources/icons/icon_err.png")
            }
        }
    }

    @Composable
    fun Image(
        contentSourceType: ContentSourceType,
        modifier: Modifier = Modifier,
        contentScale: ContentScale = ContentScale.Fit,
        colorFilter: ColorFilter? = null,
        progressIndicatorColor: Color = dark,
    ) {
        val (imageAsset, setImageAsset) = remember(contentSourceType) { mutableStateOf<ImageBitmap?>(null) }
        val scope = rememberCoroutineScope()
        Box(
            modifier = Modifier.then(modifier)
        ) {
            if (imageAsset == null) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = progressIndicatorColor
                )
                scope.launch { setImageAsset(loadImage(contentSourceType)) }
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
}

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
            withContext(Dispatchers.IO) { externalImageResource("src/main/resources/icons/icon_err.png") }
        }
    }
    url.isPathToLocalFileValid() -> {
        try {
            withContext(Dispatchers.IO) { externalImageResource(url) }
        } catch (e: Exception) {
            println("ImageLoader - ${e.message}")
            withContext(Dispatchers.IO) { externalImageResource("src/main/resources/icons/icon_err.png") }
        }
    }
    else -> {
        println("ImageLoader - url is:$url")
        withContext(Dispatchers.IO) { externalImageResource("src/main/resources/icons/icon_err.png") }
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
    val (imageAsset, setImageAsset) = remember(url) { mutableStateOf<ImageBitmap?>(null) }
    val scope = rememberCoroutineScope()
    Box(
        modifier = Modifier.then(modifier)
    ) {
        if (imageAsset == null) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = progressIndicatorColor
            )
            scope.launch { setImageAsset(loadImage(url)) }
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
