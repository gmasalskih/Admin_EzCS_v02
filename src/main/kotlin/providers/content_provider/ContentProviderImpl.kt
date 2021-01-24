package providers.content_provider

import com.dropbox.core.v2.files.DbxUserFilesRequests
import com.dropbox.core.v2.files.PathOrLink
import com.dropbox.core.v2.files.ThumbnailFormat
import com.dropbox.core.v2.files.ThumbnailSize
import providers.ContentProvider
import java.io.*

class ContentProviderImpl(private val dropbox: DbxUserFilesRequests) : ContentProvider {

    override fun isFolderExist(pathToFolder: String): Boolean {
        return try {
            dropbox.listFolder(pathToFolder.toValidPath())
            true
        } catch (e: Exception) {
            false
        }
    }

    override fun deleteFile(pathToFolder: String, fileName: String) {
        dropbox.deleteV2("${pathToFolder.toValidPath()}/$fileName")
    }

    override fun deleteFolder(pathToFolder: String) {
        dropbox.deleteV2(pathToFolder.toValidPath())
    }

    override fun getFileThumbnail(pathToFolder: String, fileName: String): ByteArray? {
        return if (isFileExist(pathToFolder.toValidPath(), fileName)) {
            dropbox.getThumbnailV2Builder(PathOrLink.path("${pathToFolder.toValidPath()}/$fileName"))
                .withFormat(ThumbnailFormat.PNG)
                .withSize(ThumbnailSize.W128H128)
                .start()
                .inputStream
                .use { inputStream -> BufferedInputStream(inputStream).use { bis -> bis.readAllBytes() } }
        } else {
            null
        }
    }

    override fun getFile(pathToFolder: String, fileName: String): ByteArray? {
        return if (isFileExist(pathToFolder.toValidPath(), fileName)) {
            dropbox.download("${pathToFolder.toValidPath()}/$fileName")
                .inputStream
                .use { inputStream -> BufferedInputStream(inputStream).use { bis -> bis.readAllBytes() } }
        } else {
            null
        }
    }

    override fun getListFileNames(pathToFolder: String): List<String> {
        return dropbox.listFolder(pathToFolder.toValidPath()).entries.map { it.name }.toList()
    }

    override fun isFileExist(pathToFolder: String, fileName: String): Boolean {
        return try {
            dropbox.listFolder(pathToFolder.toValidPath()).entries.find {
                it.name == fileName
            } != null
        } catch (e: Exception) {
            false
        }
    }

    override fun uploadFile(sourcePathToFolder: String, targetPathToFolder: String, fileName: String) {
        val file = File("$sourcePathToFolder/$fileName")
        if (!file.exists()) throw FileNotFoundException()
        if (isFileExist(targetPathToFolder, fileName)) deleteFile(targetPathToFolder, fileName)
        FileInputStream(file).use { fis ->
            dropbox.uploadBuilder("${targetPathToFolder.toValidPath()}/$fileName").uploadAndFinish(fis)
        }
    }

    private fun String.toValidPath() = if (this.contains("^[/]".toRegex())) this else "/$this"
}

