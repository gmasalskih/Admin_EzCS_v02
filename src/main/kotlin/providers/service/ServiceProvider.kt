package providers.service

import data.entitys.Entity
import providers.Service
import providers.dropbox.DropboxProvider
import providers.firebase.FirestoreProvider
import screens.State
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

    override suspend fun <T : Entity> upload(entity: T) {
        checkEntity(entity, false)
        val entityMap = mutableMapOf<String, Any>()
        val contentSet = mutableSetOf<String>()
        entity::class.declaredMemberProperties.forEach { prop ->
            when {
                prop.annotations.find { it is ContentType } != null -> {
                    val content = prop.call(entity)
                        ?: throw Exception("Entity ${entity.name} have field ${prop.name} contains null")
                    when (content) {
                        is List<*> -> {
                            val contentList = content.filterIsInstance<String>().map { pathToFileContent ->
                                addToContentSet(pathToFileContent, contentSet)
                                pathToFileContent.toValidFileName()
                            }.toList()
                            if (contentList.isEmpty()) throw Exception("Entity ${entity.name} have field ${prop.name} contains empty list or list of incompatible type.")
                            entityMap[prop.name] = contentList
                        }
                        is String -> {
                            addToContentSet(content, contentSet)
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
        firestore.uploadMap(entityMap, entity.contentsPath())
        contentSet.forEach { pathToFile -> dropbox.uploadFile(pathToFile, entity.contentsPath()) }
    }

    override suspend fun <T : Entity> retrieveEntity(collectionName: String, clazz: KClass<T>) =
        firestore.download(collectionName, clazz.java).apply { enrichEntity(this) }

    override suspend fun <T : Entity> retrieveEntities(collectionName: String, clazz: KClass<T>) =
        firestore.getCollectionItems(collectionName, clazz.java).map { entity -> enrichEntity(entity) }


    override suspend fun <T : Entity> update(entity: T) {
        //TODO implement fun update
    }

    override suspend fun <T : Entity> delete(entity: T) {
        //TODO implement fun delete
    }

    private suspend fun <T : Entity> enrichEntity(entity: T) = entity.apply {
        this::class.declaredMemberProperties.forEach { prop ->
            if (prop.annotations.find { it is ContentType } != null) {
                when (val content = prop.call(this)) {
                    is List<*> -> {
                        prop.javaField?.let { field ->
                            field.isAccessible = true
                            val newContentList = content.filterIsInstance<String>()
                                .map { fileName -> dropbox.getFileUrl(contentsPath(), fileName) }
                                .toList()
                            if (newContentList.isEmpty()) throw  Exception("Entity $name have field ${prop.name}, but it's field have empty list $content")
                            field.set(this, newContentList)
                        }
                    }
                    is String -> {
                        prop.javaField?.let { field ->
                            field.isAccessible = true
                            field.set(this, dropbox.getFileUrl(contentsPath(), content))
                        }
                    }
                    else -> {
                        throw Exception("Entity $name have field ${prop.name} contains incompatible type of ${prop.returnType}")
                    }
                }
            }
        }
    }

    private fun addToContentSet(content: String, contentSet: MutableSet<String>) {
        if (!content.isValidPathToFile()) throw Exception("$content is not valid path to file")
        if (!contentSet.add(content)) throw Exception("Each item content have to unique! $content already exist.")
    }

    private suspend fun <T : Entity> checkEntity(entity: T, isEntityHaveToExist: Boolean) {
        val isEntityOnFirestoreExist = firestore.isEntityExist(entity.contentsPath())
        val isEntityOnDropboxExist = dropbox.isEntityExist(entity.contentsPath())
        if (isEntityOnFirestoreExist != isEntityOnDropboxExist)
            throw Exception("The entity ${entity.contentsPath()} is not consistent stored!")
        if (isEntityHaveToExist != (isEntityOnFirestoreExist && isEntityOnDropboxExist))
            throw Exception("The ${entity.contentsPath()} ${if (isEntityHaveToExist) "is not" else "already"} exist!")
    }
}
