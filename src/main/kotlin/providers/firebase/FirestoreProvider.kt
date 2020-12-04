package providers.firebase

import com.google.firebase.FirebaseApp
import com.google.firebase.cloud.FirestoreClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FirestoreProvider(app: FirebaseApp) {
    private val db = FirestoreClient.getFirestore(app)

    suspend fun <T> getCollectionItems(collectionName: String, clazz: Class<T>) = withContext(Dispatchers.IO) {
        db.collection(collectionName).get().get().documents.map { it.toObject(clazz) }.toList()
    }


}