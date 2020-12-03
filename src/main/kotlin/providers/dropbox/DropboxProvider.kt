package providers.dropbox

import com.dropbox.core.DbxRequestConfig
import com.dropbox.core.v2.DbxClientV2
import com.dropbox.core.v2.files.PathOrLink
import utils.DROPBOX_TOKEN
import java.io.File
import java.io.FileInputStream

class DropboxProvider {
    private val dropbox = DbxClientV2(DbxRequestConfig.newBuilder("Admin_EzCS/2.0").build(), DROPBOX_TOKEN)

    fun getFileUrl(pathToFile: String, fileName: String): String {
        println("/$pathToFile/$fileName")
        return dropbox.files().getTemporaryLink("/$pathToFile/$fileName").link
    }

    fun ddd(){
        FileInputStream(File("")).use {

        }



    }

}