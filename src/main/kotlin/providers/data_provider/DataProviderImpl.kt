package providers.data_provider

import com.google.firebase.FirebaseApp
import com.google.firebase.cloud.FirestoreClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import providers.DataProvider

class DataProviderImpl(app: FirebaseApp) : DataProvider {
    private val db = FirestoreClient.getFirestore(app)

    @Suppress("BlockingMethodInNonBlockingContext")
    override suspend fun isDocumentExist(documentName: String): Boolean = withContext(Dispatchers.IO) {
        db.document(documentName).get().get().exists()
    }

    @Suppress("BlockingMethodInNonBlockingContext")
    override suspend fun <T> getListDocuments(collectionName: String, clazz: Class<T>): List<T> =
        withContext(Dispatchers.IO) {
            db.collection(collectionName).get().get().documents.map { it.toObject(clazz)!! }.toList()
        }

    @Suppress("BlockingMethodInNonBlockingContext")
    override suspend fun uploadDocument(dataMap: Map<String, Any>, documentName: String): Unit = withContext(Dispatchers.IO) {
        if (isDocumentExist(documentName)) throw Exception("$documentName is exist on Firestore")
        db.document(documentName).set(dataMap).get()
    }

    @Suppress("BlockingMethodInNonBlockingContext")
    override suspend fun <T> downloadDocument(documentName: String, clazz: Class<T>): T = withContext(Dispatchers.IO) {
        if (!db.document(documentName).get().get().exists()) throw Exception("$documentName is note exist on Firestore")
        db.document(documentName).get().get().toObject(clazz)!!
    }

    @Suppress("BlockingMethodInNonBlockingContext")
    override suspend fun updateDocument(dataMap: Map<String, Any>, documentName: String): Unit = withContext(Dispatchers.IO) {
        if (!isDocumentExist(documentName)) throw Exception("$documentName is note exist on Firestore")
        db.document(documentName).set(dataMap).get()
    }

    override suspend fun deleteDocument(documentName: String): Unit = withContext(Dispatchers.IO) {
        if (!isDocumentExist(documentName)) throw Exception("$documentName is note exist on Firestore")
        db.document(documentName).delete()
    }
}