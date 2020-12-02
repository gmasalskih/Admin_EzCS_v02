package providers.firebase

import com.google.cloud.storage.BlobId
import com.google.cloud.storage.BlobInfo
import com.google.firebase.cloud.StorageClient
import data.enums.ContentType
import java.io.File
import java.io.FileInputStream
import java.util.concurrent.TimeUnit

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

    suspend fun getFileUrl(contentPath: String = "", fileName: String = ""): String {
        val bucket = storageClient.bucket(bucketName)
        val storage = bucket.storage
        return storage.get(BlobId.of(bucket.name, "$contentPath$fileName")).signUrl(1, TimeUnit.MINUTES).toString()
    }
}