package providers.content_provider

import com.dropbox.core.DbxRequestConfig
import com.dropbox.core.v2.DbxClientV2
import com.dropbox.core.v2.files.PathOrLink
import com.dropbox.core.v2.files.ThumbnailFormat
import com.dropbox.core.v2.files.ThumbnailSize
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import providers.ContentStorage
import utils.DROPBOX_TOKEN
import java.io.*

class DropboxProvider : ContentStorage {
    private val dropbox = DbxClientV2(DbxRequestConfig.newBuilder("Admin_EzCS/2.0").build(), DROPBOX_TOKEN).files()

    override suspend fun isFolderExist(pathToFolder: String): Boolean = withContext(Dispatchers.IO) {
        try {
            dropbox.listFolder(pathToFolder.toValidPath())
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun deleteFile(pathToFolder: String, fileName: String): Unit = withContext(Dispatchers.IO) {
        dropbox.deleteV2("${pathToFolder.toValidPath()}/$fileName")
    }

    override suspend fun deleteFolder(pathToFolder: String): Unit = withContext(Dispatchers.IO) {
        dropbox.deleteV2(pathToFolder.toValidPath())
    }

    override suspend fun getFileThumbnail(pathToFolder: String, fileName: String): ByteArray? =
        withContext(Dispatchers.IO) {
            if (!isFileExist(pathToFolder.toValidPath(), fileName)) return@withContext null
            dropbox.getThumbnailV2Builder(PathOrLink.path("${pathToFolder.toValidPath()}/$fileName"))
                .withFormat(ThumbnailFormat.PNG)
                .withSize(ThumbnailSize.W128H128)
                .start()
                .inputStream
                .use { inputStream -> BufferedInputStream(inputStream).use { bis -> bis.readAllBytes() } }
        }

    override suspend fun getFile(pathToFolder: String, fileName: String): ByteArray? = withContext(Dispatchers.IO) {
        if (!isFileExist(pathToFolder.toValidPath(), fileName)) return@withContext null
        dropbox.download("${pathToFolder.toValidPath()}/$fileName")
            .inputStream
            .use { inputStream -> BufferedInputStream(inputStream).use { bis -> bis.readAllBytes() } }
    }

    override suspend fun getListFileNames(pathToFolder: String): List<String> = withContext(Dispatchers.IO) {
        dropbox.listFolder(pathToFolder.toValidPath()).entries.map { it.name }.toList()
    }

    override suspend fun isFileExist(pathToFolder: String, fileName: String): Boolean = withContext(Dispatchers.IO) {
        try {
            dropbox.listFolder(pathToFolder.toValidPath()).entries.find {
                it.name == fileName
            } != null
        } catch (e: Exception) {
            false
        }
    }

    @Suppress("BlockingMethodInNonBlockingContext")
    override suspend fun uploadFile(sourcePathToFolder: String, targetPathToFolder: String, fileName: String): Boolean =
        withContext(Dispatchers.IO) {
            val file = File("$sourcePathToFolder/$fileName")
            if (!file.exists()) throw FileNotFoundException()
            if (isFileExist(targetPathToFolder, fileName)) deleteFile(targetPathToFolder, fileName)
            FileInputStream(file).use { fis ->
                dropbox.uploadBuilder("${targetPathToFolder.toValidPath()}/$fileName").uploadAndFinish(fis)
            }
            true
        }
}

private fun String.toValidPath() = if (this.contains("^[/]".toRegex())) this else "/$this"