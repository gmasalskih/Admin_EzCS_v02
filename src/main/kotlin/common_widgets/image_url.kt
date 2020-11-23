package common_widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageAsset
import androidx.compose.ui.graphics.asImageAsset
import androidx.compose.ui.layout.ContentScale
import kotlinx.coroutines.*
import org.jetbrains.skija.Image
import utils.isValidURL
import java.io.BufferedInputStream
import java.net.URL

@Composable
fun ImageUrl(
    url: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit
) {
    val (imageAsset, setImageAsset) = remember { mutableStateOf<ImageAsset?>(null) }
    val (oldUrl, setOldUrl) = remember { mutableStateOf(url) }
    val job = Job()
    onCommit {
        if (oldUrl != url) {
            setImageAsset(null)
            setOldUrl(url)
        }
    }
    onDispose {
        job.cancel()
    }
    if (url.isValidURL() && imageAsset == null) {
        val bis = BufferedInputStream(URL(url).openStream())
        GlobalScope.launch(job) {
            withContext(Dispatchers.IO + job) {
                setImageAsset(Image.makeFromEncoded(bis.readAllBytes()).asImageAsset())
            }
            println(Thread.currentThread())
            bis.close()
        }
    }

    Box(
        modifier = Modifier.then(modifier)
    ) {
        if (imageAsset == null) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            Image(
                modifier = Modifier.then(modifier),
                asset = imageAsset,
                contentScale = contentScale
            )
        }
    }
}