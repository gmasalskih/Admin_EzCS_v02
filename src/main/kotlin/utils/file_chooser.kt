package utils

import data.types.ContentSourceType
import data.types.FileType
import java.awt.FileDialog
import java.awt.Frame
import java.io.File

fun fileChooser(title: String, fileType: FileType): String? {
    var path: String? = null
    val dialog = FileDialog(Frame(), title).apply {
        mode = FileDialog.LOAD
        file = "*.${fileType.extension}"
        isVisible = true
    }
    if (dialog.directory != null && dialog.file != null) {
        path = "${dialog.directory}${dialog.file}".replace("\\", "/")
    }
    dialog.dispose()
    return if (path != null && File(path).exists()) path else null
}

inline fun fileChooser(
    title: String,
    fileType: FileType,
    oldItem: ContentSourceType,
    call: (ContentSourceType) -> Unit
) {
    fileChooser(title, fileType)?.let {
        val newItem = ContentSourceType.File(it, fileType)
        if (oldItem != newItem) call(newItem)
    }
}

inline fun fileChooser(
    title: String,
    fileType: FileType,
    listItems: List<ContentSourceType>,
    call: (ContentSourceType) -> Unit
) {
    fileChooser(title, fileType)?.let {
        val newItem = ContentSourceType.File(it, fileType)
        if (!listItems.contains(newItem)) call(newItem)
    }
}



