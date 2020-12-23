package utils

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import java.io.File
import java.io.FileInputStream
import java.net.URL
import org.jetbrains.skija.Image as ImageLoader

fun String.toValidId() = this.replace("[^a-zA-Z0-9_\\s]".toRegex(), "")
    .replace("[\\s]+".toRegex(), "_")
    .replace("[_]+".toRegex(), "_")
    .toLowerCase()

fun String.toValidName(): String {
    return this.replace("[^a-zA-Z0-9_ ()]".toRegex(), "")
        .replace("[_]+".toRegex(), "_")
        .replace("[ ]+".toRegex(), " ")
        .replace("[(]+".toRegex(), "(")
        .replace("[)]+".toRegex(), ")")
}

fun String.toValidXP(): String {
    return this.replace("[^0-9]".toRegex(), "") + "XP"
}

fun externalImageResource(path: String): ImageBitmap {
    return ImageLoader.makeFromEncoded(FileInputStream(File(path)).readAllBytes()).asImageBitmap()
}

fun String.toValidFileName(): String {
    val fileName = this.substringAfterLast("/")
    if (fileName.matches("^[0-9a-zA-Z_ \\-]+\\.[a-zA-Z0-9]+$".toRegex())) return fileName
    throw Exception("$this can't to convert valid file name")
}

fun String.toValidFolder(): String {
    if (!this.isPathToLocalFileValid()) return this
    return this.substringBeforeLast("/")
}

fun String.toValidOrder() = this.replace("[^0-9]".toRegex(), "").let { order ->
    if (order.isEmpty() || order.toInt() < 1) return@let ""
    if (order.length > 3) order.take(3).toInt().toString()
    else order.toInt().toString()
}


fun String.isValidURL() = try {
    URL(this)
    true
} catch (e: Exception) {
    false
}

fun String.isPathToLocalFileValid() = File(this).exists()