package providers.firebase

import com.google.firebase.FirebaseApp
import com.google.firebase.cloud.FirestoreClient

class FirestoreProvider(app: FirebaseApp) {
    private val db = FirestoreClient.getFirestore(app)

    suspend fun <T> getCollectionItems(collectionName: String, clazz: Class<T>) =
        db.collection(collectionName).get().get().documents.map { it.toObject(clazz) }.toList()

}