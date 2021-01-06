package providers

interface ContentProvider {
    fun isFolderExist(pathToFolder: String): Boolean
    fun deleteFile(pathToFolder: String, fileName: String)
    fun deleteFolder(pathToFolder: String)
    fun getFileThumbnail(pathToFolder: String, fileName: String): ByteArray?
    fun getFile(pathToFolder: String, fileName: String): ByteArray?
    fun getListFileNames(pathToFolder: String): List<String>
    fun isFileExist(pathToFolder: String, fileName: String): Boolean
    fun uploadFile(sourcePathToFolder: String, targetPathToFolder: String, fileName: String)
}