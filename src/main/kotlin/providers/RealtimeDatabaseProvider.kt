package providers

interface RealtimeDatabaseProvider {
    suspend fun saveDocuments(document: Map<String, Any>)
    suspend fun clear()
    suspend fun <T : Any> getDocument(documentName: String, type: Class<T>): T
    suspend fun <T : Any> getMapOfDocuments(type: Class<T>): Map<String, T>
}