package providers.firebase

import com.google.firebase.FirebaseApp
import com.google.firebase.cloud.FirestoreClient
import data.entitys.Entity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FirestoreProvider(app: FirebaseApp) {
    private val db = FirestoreClient.getFirestore(app)

    @Suppress("BlockingMethodInNonBlockingContext")
    suspend fun isEntityExist(contentsPath: String) = withContext(Dispatchers.IO) {
        db.document(contentsPath).get().get().exists()
    }

    @Suppress("BlockingMethodInNonBlockingContext")
    suspend fun <T> getCollectionItems(collectionName: String, clazz: Class<T>) = withContext(Dispatchers.IO) {
        db.collection(collectionName).get().get().documents.map { it.toObject(clazz)!! }.toList()
    }

    @Suppress("BlockingMethodInNonBlockingContext")
    suspend fun <E : Entity> uploadEntity(entity: E) = withContext(Dispatchers.IO) {
        if (isEntityExist(entity.getDocumentName())) throw Exception("${entity.getDocumentName()} is exist on Firestore")
        db.document(entity.getDocumentName()).set(entity).get()
    }

    @Suppress("BlockingMethodInNonBlockingContext")
    suspend fun uploadMap(dataMap: Map<String, Any>, contentsPath: String) = withContext(Dispatchers.IO) {
        if (isEntityExist(contentsPath)) throw Exception("$contentsPath is exist on Firestore")
        db.document(contentsPath).set(dataMap).get()
    }

    @Suppress("BlockingMethodInNonBlockingContext")
    suspend fun <T> download(documentName: String, clazz: Class<T>): T = withContext(Dispatchers.IO) {
        if (!db.document(documentName).get().get().exists()) throw Exception("$documentName is note exist on Firestore")
        db.document(documentName).get().get().toObject(clazz)!!
    }

    @Suppress("BlockingMethodInNonBlockingContext")
    suspend fun <E : Entity> update(entity: E) = withContext(Dispatchers.IO) {
        if (!isEntityExist(entity.getDocumentName())) throw Exception("${entity.getDocumentName()} is note exist on Firestore")
        db.document(entity.getDocumentName()).set(entity).get()
    }

    suspend fun delete(path: String) = withContext(Dispatchers.IO) {
        if (!isEntityExist(path)) throw Exception("$path is note exist on Firestore")
        db.document(path).delete()
    }
}