package data.types

sealed class ContentSourceType(val value: String) {
    data class URL(val url: String) : ContentSourceType(url)
    data class DropBoxThumbnail(
        val pathToFolder: String,
        val fileName: String
    ) : ContentSourceType("$pathToFolder/$fileName")

    data class DropBoxRaw(
        val pathToFolder: String,
        val fileName: String
    ) : ContentSourceType("$pathToFolder/$fileName")

    data class File(val pathToFile: String) : ContentSourceType(pathToFile)
    data class Resource(val pathToResource: String) : ContentSourceType(pathToResource)
    object Empty : ContentSourceType("")
}