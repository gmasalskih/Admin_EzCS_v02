package providers.dropbox

import com.dropbox.core.DbxRequestConfig
import com.dropbox.core.v2.DbxClientV2
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import utils.DROPBOX_TOKEN
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException

class DropboxProvider {
    private val dropbox = DbxClientV2(DbxRequestConfig.newBuilder("Admin_EzCS/2.0").build(), DROPBOX_TOKEN)

    suspend fun getFileUrl(pathToFile: String, fileName: String): String = withContext(Dispatchers.IO) {
        dropbox.files().getTemporaryLink("${pathToFile.toValidPath()}/$fileName").link
    }

    suspend fun isEntityExist(contentsPath: String) = withContext(Dispatchers.IO) {
        try {
            dropbox.files().listFolder(contentsPath.toValidPath())
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun delete(path: String) = withContext(Dispatchers.IO) {
        dropbox.files().deleteV2(path.toValidPath())
    }

    suspend fun update() = withContext(Dispatchers.IO) {
        dropbox.files().listFolder("/MAP_HOLDER/vertigo").entries.forEach {

            println(it.name)
        }
    }

    suspend fun getListItems(pathToFolder: String) = withContext(Dispatchers.IO) {
        dropbox.files().listFolder(pathToFolder.toValidPath()).entries.map { it.name }.toList()
    }

    private fun isFileExist(pathToFile: String, fileName: String) =
        dropbox.files().listFolder(pathToFile.toValidPath()).entries.find {
            it.name == fileName
        } != null

    @Suppress("BlockingMethodInNonBlockingContext")
    suspend fun uploadFile(sourceFullPathToFile: String, targetFolder: String): Boolean = withContext(Dispatchers.IO) {
        val file = File(sourceFullPathToFile)
        if (!file.exists()) throw FileNotFoundException()
        val fileName: String = sourceFullPathToFile.split("[/]".toRegex()).last()
        if (isFileExist(targetFolder, fileName)) delete("${targetFolder.toValidPath()}/$fileName")
        FileInputStream(file).use { fis ->
            dropbox.files().uploadBuilder("${targetFolder.toValidPath()}/$fileName").uploadAndFinish(fis)
        }
        true
    }
}

private fun String.toValidPath() = if (this.contains("^[/]".toRegex())) this else "/$this"
