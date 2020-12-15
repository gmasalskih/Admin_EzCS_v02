package providers

import data.entitys.Entity
import kotlin.reflect.KClass

interface Service {
    suspend fun <T : Entity> upload(entity: T)
    suspend fun <T : Entity> retrieveEntity(documentName: String, clazz: KClass<T>): T
    suspend fun <T : Entity> retrieveEntities(collectionName: String, clazz: KClass<T>): List<T>
    suspend fun <T : Entity> update(entity: T)
    suspend fun delete(path: String)
}