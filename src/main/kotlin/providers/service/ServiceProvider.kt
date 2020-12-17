package providers.service

import data.entitys.Entity
import providers.Service
import providers.dropbox.DropboxProvider
import providers.firebase.FirestoreProvider
import utils.ContentType
import utils.DataType
import utils.isValidPathToFile
import utils.toValidFileName
import kotlin.reflect.KClass
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.jvm.javaField

class ServiceProvider(
    private val firestore: FirestoreProvider,
    private val dropbox: DropboxProvider
) : Service {

    override suspend fun <T : Entity> update(entity: T) {
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
                                if (pathToFileContent.isValidPathToFile())
                                    addToUploadContentSet(pathToFileContent, uploadContentSet)
                                addToFullContentSet(pathToFileContent, fullContentSet)
                                pathToFileContent.split("/").last()
                            }.toList()
                            if (fullContentSet.isEmpty()) throw Exception("Entity ${entity.name} have field ${prop.name} contains empty list or list of incompatible type.")
                            entityMap[prop.name] = contentList
                        }
                        is String -> {
                            if (content.isValidPathToFile()) addToUploadContentSet(content, uploadContentSet)
                            addToFullContentSet(content, fullContentSet)
                            entityMap[prop.name] = content.split("/").last()
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
        println("---uploadContentSet ${uploadContentSet}")
        println("---fullContentSet ${fullContentSet}")
        firestore.update(entityMap, entity.getDocumentName())
        uploadContentSet.forEach { pathToFile -> dropbox.uploadFile(pathToFile, entity.getDocumentName()) }
        dropbox.getListItems(entity.getDocumentName()).forEach { fileName ->
            if (!fullContentSet.contains(fileName)) dropbox.delete("${entity.getDocumentName()}/$fileName")
        }
    }

    override suspend fun <T : Entity> upload(entity: T) {
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
        firestore.upload(entityMap, entity.getDocumentName())
        uploadContentSet.forEach { pathToFile -> dropbox.uploadFile(pathToFile, entity.getDocumentName()) }
    }

    override suspend fun <T : Entity> retrieveEntity(documentName: String, clazz: KClass<T>) =
        firestore.download(documentName, clazz.java).apply { enrichEntity(this) }

    override suspend fun <T : Entity> retrieveRawEntity(documentName: String, clazz: KClass<T>) =
        firestore.download(documentName, clazz.java)

    override suspend fun <T : Entity> retrieveEntities(collectionName: String, clazz: KClass<T>) =
        firestore.getCollectionItems(collectionName, clazz.java).map { entity -> enrichEntity(entity) }

    override suspend fun delete(path: String) {
        firestore.delete(path)
        dropbox.delete("/$path")
    }

    private suspend fun <T : Entity> enrichEntity(entity: T) = entity.apply {
        this::class.declaredMemberProperties.forEach { prop ->
            if (prop.annotations.find { it is ContentType } != null) {
                when (val content = prop.call(this)) {
                    is List<*> -> {
                        prop.javaField?.let { field ->
                            field.isAccessible = true
                            val newContentList = content.filterIsInstance<String>()
                                .map { fileName -> dropbox.getFileUrl(getDocumentName(), fileName) }
                                .toList()
                            if (newContentList.isEmpty()) throw  Exception("Entity $name have field ${prop.name}, but it's field have empty list $content")
                            field.set(this, newContentList)
                        }
                    }
                    is String -> {
                        prop.javaField?.let { field ->
                            field.isAccessible = true
                            field.set(this, dropbox.getFileUrl(getDocumentName(), content))
                        }
                    }
                    else -> {
                        throw Exception("Entity $name have field ${prop.name} contains incompatible type of ${prop.returnType}")
                    }
                }
            }
        }
    }

    private fun addToFullContentSet(content: String, contentSet: MutableSet<String>) {
        if (!contentSet.add(
                content.split("/").last()
            )
        ) throw Exception("Each item content have to unique! $content already exist.")
    }

    private fun addToUploadContentSet(content: String, contentSet: MutableSet<String>) {
        if (!content.isValidPathToFile()) throw Exception("$content is not valid path to file")
        if (!contentSet.add(content)) throw Exception("Each item content have to unique! $content already exist.")
    }

    private suspend fun <T : Entity> checkEntity(entity: T, isEntityHaveToExist: Boolean) {
        val isEntityOnFirestoreExist = firestore.isEntityExist(entity.getDocumentName())
        val isEntityOnDropboxExist = dropbox.isEntityExist(entity.getDocumentName())
        if (isEntityOnFirestoreExist != isEntityOnDropboxExist)
            throw Exception("The entity ${entity.getDocumentName()} is not consistent stored!")
        if (isEntityHaveToExist != (isEntityOnFirestoreExist && isEntityOnDropboxExist))
            throw Exception("The ${entity.getDocumentName()} ${if (isEntityHaveToExist) "is not" else "already"} exist!")
    }
}