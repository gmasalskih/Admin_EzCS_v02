package providers

import data.entitys.Entity
import data.entitys.blueprint_weapon.BlueprintWeapon
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import kotlin.reflect.KClass

interface ServiceProvider {
    suspend fun <T : Entity> uploadEntity(entity: T)
    suspend fun <T : Entity> getEntityAsync(documentName: String, clazz: KClass<T>): Deferred<T>
    suspend fun <T : Entity> getListEntitiesAsync(collectionName: String, clazz: KClass<T>): Deferred<List<T>>
    suspend fun <T : Entity> updateEntity(entity: T)
    suspend fun deleteEntity(documentName: String)
    suspend fun getListNameOfBlueprintWeaponAsync(): Deferred<List<String>>
    suspend fun updateMapOfBlueprintWeaponFromSourceFile(pathToSourceFile: String)
}