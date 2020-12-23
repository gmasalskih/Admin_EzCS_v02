package providers

interface DataStorage {
    suspend fun isDocumentExist(documentName: String): Boolean
    suspend fun <T> getListDocuments(collectionName: String, clazz: Class<T>): List<T>
    suspend fun uploadDocument(dataMap: Map<String, Any>, documentName: String)
    suspend fun <T> downloadDocument(documentName: String, clazz: Class<T>): T
    suspend fun updateDocument(dataMap: Map<String, Any>, documentName: String)
    suspend fun deleteDocument(documentName: String)
}