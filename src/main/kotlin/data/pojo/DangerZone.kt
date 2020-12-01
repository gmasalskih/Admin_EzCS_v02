package data.pojo

import data.Entity
import data.enums.FirestoreCollections

data class DangerZone(
    val id: String = "",
    val name: String = "",
    val logo: String = "",
    val collection: FirestoreCollections = FirestoreCollections.DANGER_ZONE,
) : Entity {
    fun getContentsPath() = "${collection.name}/$id/"
}