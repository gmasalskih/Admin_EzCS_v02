package utils

import java.awt.FileDialog
import java.awt.Frame

fun fileChooser(title: String): String {
    val dialog = FileDialog(Frame(), title).apply {
        mode = FileDialog.LOAD
        isVisible = true
    }
    val path = "${dialog.directory}${dialog.file}"
    dialog.dispose()
    return path
}