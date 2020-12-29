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
import org.koin.core.KoinComponent
import org.koin.core.inject
import providers.ContentStorage
import ui.dark
import ui.Icons
import utils.externalImageResource
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
            when(contentSourceType.fileType){
                FileType.PNG -> {
                    withContext(Dispatchers.IO) {
                        externalImageResource(contentSourceType.pathToFile)
                    }
                }
                FileType.GIF -> {
                    withContext(Dispatchers.IO) {
                        externalImageResource(contentSourceType.pathToFile)
                    }
                }
                FileType.MP4 -> {
                    withContext(Dispatchers.IO) {
                        externalImageResource("src/main/resources/${Icons.Video}")
                    }
                }
                FileType.SVG -> {
                    withContext(Dispatchers.IO) {
                        externalImageResource("src/main/resources/${Icons.SVG}")
                    }
                }
                FileType.TXT -> {
                    withContext(Dispatchers.IO) {
                        externalImageResource("src/main/resources/${Icons.TextFile}")
                    }
                }
            }
        }
        is ContentSourceType.Resource -> {
            withContext(Dispatchers.IO) {
                externalImageResource("src/main/resources/${contentSourceType.pathToResource}")
            }
        }
        is ContentSourceType.Empty -> {
            withContext(Dispatchers.IO) {
                externalImageResource("src/main/resources/${Icons.Err}")
            }
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
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = progressIndicatorColor
                )
                scope.launch { setImageAsset(loadImage(content)) }
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