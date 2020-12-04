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
        println("/$pathToFile/$fileName")
        dropbox.files().getTemporaryLink("/$pathToFile/$fileName").link
    }

    @Suppress("BlockingMethodInNonBlockingContext")
    suspend fun uploadFile(sourceFullPathToFile: String, targetFolder: String): Boolean = withContext(Dispatchers.IO) {
        val file = File(sourceFullPathToFile)
        if (!file.exists()) throw FileNotFoundException()
        val fileName: String = sourceFullPathToFile.split("[/]".toRegex()).last()
        FileInputStream(file).use { fis ->
            dropbox.files().uploadBuilder("/$targetFolder/$fileName").uploadAndFinish(fis)
        }
        true
    }
}