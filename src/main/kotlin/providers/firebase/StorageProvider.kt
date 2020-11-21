package providers.firebase

import com.google.firebase.cloud.StorageClient
import data.enums.ContentType
import java.io.File
import java.io.FileInputStream

class StorageProvider(
    private val storageClient: StorageClient,
    private val bucketName: String,
) {
    fun uploadFile(
        sourcePath: String,
        sourceFileName: String,
        targetPath: String,
        targetFileName: String,
        contentType: ContentType
    ) {
        storageClient.bucket(bucketName).create(
            "$targetPath$targetFileName",
            FileInputStream(File("$sourcePath$sourceFileName")),
            contentType.value
        )
    }
}