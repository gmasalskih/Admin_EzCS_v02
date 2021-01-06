package providers.data_provider

import com.google.firebase.FirebaseApp
import com.google.firebase.cloud.FirestoreClient
import providers.DataProvider

class DataProviderImpl(app: FirebaseApp) : DataProvider {
    private val db = FirestoreClient.getFirestore(app)

    override fun isDocumentExist(documentName: String): Boolean {
        return db.document(documentName).get().get().exists()
    }

    override fun <T> getListDocuments(collectionName: String, clazz: Class<T>): List<T> {
        return db.collection(collectionName).get().get().documents.map { it.toObject(clazz)!! }.toList()
    }

    override fun uploadDocument(dataMap: Map<String, Any>, documentName: String) {
        if (isDocumentExist(documentName)) throw Exception("$documentName is exist on Firestore")
        db.document(documentName).set(dataMap).get()
    }

    override fun <T> downloadDocument(documentName: String, clazz: Class<T>): T {
        if (!db.document(documentName).get().get().exists()) throw Exception("$documentName is note exist on Firestore")
        return db.document(documentName).get().get().toObject(clazz)!!
    }

    override fun updateDocument(dataMap: Map<String, Any>, documentName: String) {
        if (!isDocumentExist(documentName)) throw Exception("$documentName is note exist on Firestore")
        db.document(documentName).set(dataMap).get()
    }

    override fun deleteDocument(documentName: String) {
        if (!isDocumentExist(documentName)) throw Exception("$documentName is note exist on Firestore")
        db.document(documentName).delete()
    }
}