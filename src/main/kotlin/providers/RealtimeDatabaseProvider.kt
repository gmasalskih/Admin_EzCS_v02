package providers

interface RealtimeDatabaseProvider {
    suspend fun saveDocument(document: Map<String, Any>): Boolean
    suspend fun clear(): Boolean
    suspend fun <T : Any> getDocument(documentName: String, type: Class<T>): T
    suspend fun getDocuments(): Map<*, *>
}