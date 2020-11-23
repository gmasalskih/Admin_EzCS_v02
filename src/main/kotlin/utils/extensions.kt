package utils

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.ImageAsset
import androidx.compose.ui.graphics.asImageAsset
import androidx.compose.ui.res.imageResource
import kotlinx.coroutines.*
import java.io.BufferedInputStream
import java.io.File
import java.io.FileInputStream
import java.net.URL
import org.jetbrains.skija.Image as ImageLoader

fun String.toValidId() = this.replace("[^a-zA-Z0-9_]".toRegex(), "")
    .replace("[_]+".toRegex(), "_")
    .toLowerCase()

fun String.toValidName(): String {
    return this.replace("[^a-zA-Z0-9_ ()]".toRegex(), "")
        .replace("[_]+".toRegex(), "_")
        .replace("[ ]+".toRegex(), " ")
        .replace("[(]+".toRegex(), "(")
        .replace("[)]+".toRegex(), ")")
}

fun externalImageResource(path: String): ImageAsset {
    return ImageLoader.makeFromEncoded(FileInputStream(File(path)).readAllBytes()).asImageAsset()
}

fun urlImageResource(url: String, asset: ImageAsset, l: (ImageAsset) -> Unit): ImageAsset {
    CoroutineScope(Dispatchers.IO).launch {
        val ia = async(Dispatchers.IO) {
            delay(5000)
            ImageLoader.makeFromEncoded(BufferedInputStream(URL(url).openStream()).readAllBytes()).asImageAsset()
        }.await()
        l(ia)
    }

    return asset
}

fun String.isValidURL() = try {
    URL(this)
    true
} catch (e: Exception) {
    false
}
