package providers

interface DataProvider {
    fun isDocumentExist(documentName: String): Boolean
    fun <T> getListDocuments(collectionName: String, clazz: Class<T>): List<T>
    fun uploadDocument(dataMap: Map<String, Any>, documentName: String)
    fun <T> downloadDocument(documentName: String, clazz: Class<T>): T
    fun updateDocument(dataMap: Map<String, Any>, documentName: String)
    fun deleteDocument(documentName: String)
}