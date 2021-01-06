package providers.service_provider

import data.entitys.Entity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import providers.ContentProvider
import providers.Service
import providers.DataProvider
import utils.*
import kotlin.reflect.KClass
import kotlin.reflect.full.declaredMemberProperties

class ServiceProviderImpl(
    private val dataProvider: DataProvider,
    private val contentProvider: ContentProvider
) : Service {

    override suspend fun <T : Entity> updateEntity(entity: T) {
        checkEntity(entity, true)
        val entityMap = mutableMapOf<String, Any>()
        val uploadContentSet = mutableSetOf<String>()
        val fullContentSet = mutableSetOf<String>()
        entity::class.declaredMemberProperties.forEach { prop ->
            when {
                prop.annotations.find { it is ContentType } != null -> {
                    val content = prop.call(entity)
                        ?: throw Exception("Entity ${entity.name} have field ${prop.name} contains null")
                    when (content) {
                        is List<*> -> {
                            val contentList = content.filterIsInstance<String>().map { pathToFileContent ->
                                checkPathToFile(entity.getDocumentName(), pathToFileContent)
                                if (pathToFileContent.isPathToLocalFileValid())
                                    addToUploadContentSet(pathToFileContent, uploadContentSet)
                                addToFullContentSet(pathToFileContent, fullContentSet)
                                pathToFileContent.toValidFileName()
                            }.toList()
                            if (fullContentSet.isEmpty()) throw Exception("Entity ${entity.name} have field ${prop.name} contains empty list or list of incompatible type.")
                            entityMap[prop.name] = contentList
                        }
                        is String -> {
                            checkPathToFile(entity.getDocumentName(), content)
                            if (content.isPathToLocalFileValid()) addToUploadContentSet(content, uploadContentSet)
                            addToFullContentSet(content, fullContentSet)
                            entityMap[prop.name] = content.toValidFileName()
                        }
                        else -> {
                            throw Exception("Entity ${entity.name} have field ${prop.name} contains incompatible type of ${prop.returnType}")
                        }
                    }
                }
                prop.annotations.find { it is DataType } != null -> {
                    prop.call(entity)?.let { obj -> entityMap.put(prop.name, obj) }
                }
            }
        }
        dataProvider.updateDocument(entityMap, entity.getDocumentName())
        uploadContentSet.forEach { fullPathToFile ->
            contentProvider.uploadFile(
                fullPathToFile.toValidFolder(),
                entity.getDocumentName(),
                fullPathToFile.toValidFileName()
            )
        }
        contentProvider.getListFileNames(entity.getDocumentName()).forEach { fileName ->
            if (!fullContentSet.contains(fileName)) contentProvider.deleteFile(entity.getDocumentName(), fileName)
        }
    }

    override suspend fun <T : Entity> uploadEntity(entity: T) {
        checkEntity(entity, false)
        val entityMap = mutableMapOf<String, Any>()
        val uploadContentSet = mutableSetOf<String>()
        entity::class.declaredMemberProperties.forEach { prop ->
            when {
                prop.annotations.find { it is ContentType } != null -> {
                    val content = prop.call(entity)
                        ?: throw Exception("Entity ${entity.name} have field ${prop.name} contains null")
                    when (content) {
                        is List<*> -> {
                            val contentList = content.filterIsInstance<String>().map { pathToFileContent ->
                                addToUploadContentSet(pathToFileContent, uploadContentSet)
                                pathToFileContent.toValidFileName()
                            }.toList()
                            if (contentList.isEmpty()) throw Exception("Entity ${entity.name} have field ${prop.name} contains empty list or list of incompatible type.")
                            entityMap[prop.name] = contentList
                        }
                        is String -> {
                            addToUploadContentSet(content, uploadContentSet)
                            entityMap[prop.name] = content.toValidFileName()
                        }
                        else -> {
                            throw Exception("Entity ${entity.name} have field ${prop.name} contains incompatible type of ${prop.returnType}")
                        }
                    }
                }
                prop.annotations.find { it is DataType } != null -> {
                    prop.call(entity)?.let { obj -> entityMap.put(prop.name, obj) }
                }
            }
        }
        dataProvider.uploadDocument(entityMap, entity.getDocumentName())
        uploadContentSet.forEach { fullPathToFile ->
            contentProvider.uploadFile(
                fullPathToFile.toValidFolder(),
                entity.getDocumentName(),
                fullPathToFile.toValidFileName()
            )
        }
    }

    override suspend fun <T : Entity> getEntity(documentName: String, clazz: KClass<T>) =
        dataProvider.downloadDocument(documentName, clazz.java)

    override suspend fun <T : Entity> getListEntities(collectionName: String, clazz: KClass<T>) =
        withContext(Dispatchers.IO) {
            dataProvider.getListDocuments(collectionName, clazz.java)
        }

    override suspend fun deleteEntity(documentName: String) {
        dataProvider.deleteDocument(documentName)
        contentProvider.deleteFolder(documentName)
    }

    private fun addToFullContentSet(content: String, contentSet: MutableSet<String>) {
        if (!contentSet.add(content.toValidFileName()))
            throw Exception("Each item content have to unique! $content already exist.")
    }

    private fun addToUploadContentSet(content: String, contentSet: MutableSet<String>) {
        if (!content.isPathToLocalFileValid()) throw Exception("$content is not valid path to file")
        if (!contentSet.add(content)) throw Exception("Each item content have to unique! $content already exist.")
    }

    private suspend fun <T : Entity> checkEntity(entity: T, isEntityHaveToExist: Boolean) {
        val isEntityOnFirestoreExist = dataProvider.isDocumentExist(entity.getDocumentName())
        val isEntityOnDropboxExist = contentProvider.isFolderExist(entity.getDocumentName())
        if (isEntityOnFirestoreExist != isEntityOnDropboxExist)
            throw Exception("The entity ${entity.getDocumentName()} is not consistent stored!")
        if (isEntityHaveToExist != (isEntityOnFirestoreExist && isEntityOnDropboxExist))
            throw Exception("The ${entity.getDocumentName()} ${if (isEntityHaveToExist) "is not" else "already"} exist!")
    }

    private suspend fun checkPathToFile(documentName: String, pathToFile: String) {
        if (
            pathToFile.isPathToLocalFileValid() ||
            contentProvider.isFileExist(documentName, pathToFile.toValidFileName())
        ) return
        throw Exception("Entity have field contains not valid pathToFile: $pathToFile")
    }
}