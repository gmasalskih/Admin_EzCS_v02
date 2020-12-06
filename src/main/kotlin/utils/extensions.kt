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
    if (!this.isValidPathToFile()) return this
    return this.split("/").last()
}

fun String.isValidURL() = try {
    URL(this)
    true
} catch (e: Exception) {
    false
}

fun String.isValidPathToFile() = File(this).exists()