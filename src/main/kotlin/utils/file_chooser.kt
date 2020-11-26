package utils

import java.awt.FileDialog
import java.awt.Frame
import java.io.File

fun fileChooser(title: String, fileType: String): String? {
    var path: String? = null
    val dialog = FileDialog(Frame(), title).apply {
        mode = FileDialog.LOAD
        file = "*.$fileType"
        isVisible = true
    }
    if (dialog.directory != null && dialog.file != null) {
        path = "${dialog.directory}${dialog.file}".replace("\\", "/")
    }
    dialog.dispose()
    return if (path != null && File(path).exists()) path else null
}