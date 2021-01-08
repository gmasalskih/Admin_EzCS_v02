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
import data.types.FileType
import kotlinx.coroutines.*
import org.jetbrains.skija.Image
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

import providers.ContentProvider
import ui.dark
import ui.Icons
import utils.externalImageResource
import java.io.BufferedInputStream
import java.net.URL

object ImageLoader : KoinComponent {
    private val contentProvider by inject<ContentProvider>()

    @Suppress("BlockingMethodInNonBlockingContext")
    private suspend fun loadImageAsync(contentSourceType: ContentSourceType, parentContext: CoroutineScope) =
        parentContext.async(Dispatchers.IO) {
            var imageBitmap: ImageBitmap? = null
            repeat(10) {
                if (imageBitmap == null) {
                    imageBitmap = withTimeoutOrNull(3000) {
                        return@withTimeoutOrNull try {
                            getImageBitmap(contentSourceType)
                        } catch (e: Exception) {
                            e.printStackTrace()
                            externalImageResource("src/main/resources/${Icons.Err}")
                        }
                    }
                } else {
                    return@async imageBitmap
                }
            }
            externalImageResource("src/main/resources/${Icons.Err}")
        }


    private fun getImageBitmap(contentSourceType: ContentSourceType): ImageBitmap? = when (contentSourceType) {
        is ContentSourceType.ContentStorageThumbnail -> {
            contentProvider.getFileThumbnail(contentSourceType.pathToFolder, contentSourceType.fileName)
                ?.let { byteArray ->
                    Image.makeFromEncoded(byteArray).asImageBitmap()
                }
        }
        is ContentSourceType.ContentStorageOriginal -> {
            contentProvider.getFile(contentSourceType.pathToFolder, contentSourceType.fileName)?.let { byteArray ->
                Image.makeFromEncoded(byteArray).asImageBitmap()
            }
        }
        is ContentSourceType.URL -> {
            URL(contentSourceType.url).openStream().use { inputStream ->
                BufferedInputStream(inputStream).use { bufferedInputStream ->
                    bufferedInputStream.readAllBytes()?.let { byteArray ->
                        Image.makeFromEncoded(byteArray).asImageBitmap()
                    }
                }
            }
        }
        is ContentSourceType.File -> {
            when (contentSourceType.fileType) {
                FileType.PNG -> {
                    externalImageResource(contentSourceType.pathToFile)
                }
                FileType.GIF -> {
                    externalImageResource(contentSourceType.pathToFile)
                }
                FileType.MP4 -> {
                    externalImageResource("src/main/resources/${Icons.Video}")
                }
                FileType.SVG -> {
                    externalImageResource("src/main/resources/${Icons.SVG}")
                }
                FileType.TXT -> {
                    externalImageResource("src/main/resources/${Icons.TextFile}")
                }
            }
        }
        is ContentSourceType.Resource -> {
            externalImageResource("src/main/resources/${contentSourceType.pathToResource}")
        }
        is ContentSourceType.Empty -> {
            externalImageResource("src/main/resources/${Icons.Err}")
        }
    }

    @Composable
    fun Image(
        content: ContentSourceType,
        modifier: Modifier = Modifier,
        contentScale: ContentScale = ContentScale.Fit,
        colorFilter: ColorFilter? = null,
        progressIndicatorColor: Color = dark,
    ) {
        val (imageAsset, setImageAsset) = remember(content) { mutableStateOf<ImageBitmap?>(null) }
        val scope = rememberCoroutineScope()
        Box(
            modifier = Modifier.then(modifier)
        ) {
            if (imageAsset == null) {
                scope.launch { setImageAsset(loadImageAsync(content, this).await()) }
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
}