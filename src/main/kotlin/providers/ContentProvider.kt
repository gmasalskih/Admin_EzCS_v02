package providers

interface ContentProvider {
    suspend fun isFolderExist(pathToFolder: String): Boolean
    suspend fun deleteFile(pathToFolder: String, fileName: String)
    suspend fun deleteFolder(pathToFolder: String)
    suspend fun getFileThumbnail(pathToFolder: String, fileName: String): ByteArray?
    suspend fun getFile(pathToFolder: String, fileName: String): ByteArray?
    suspend fun getListFileNames(pathToFolder: String): List<String>
    suspend fun isFileExist(pathToFolder: String, fileName: String): Boolean
    suspend fun uploadFile(sourcePathToFolder: String, targetPathToFolder: String, fileName: String): Boolean
}