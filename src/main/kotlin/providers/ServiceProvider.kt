package providers

import data.entitys.Entity
import kotlinx.coroutines.Job
import kotlin.reflect.KClass

interface ServiceProvider {
    suspend fun <T : Entity> uploadEntity(entity: T)
    suspend fun <T : Entity> getEntity(documentName: String, clazz: KClass<T>): T
    suspend fun <T : Entity> getListEntities(collectionName: String, clazz: KClass<T>): List<T>
    suspend fun <T : Entity> updateEntity(entity: T)
    suspend fun deleteEntity(documentName: String)
    suspend fun test()
}