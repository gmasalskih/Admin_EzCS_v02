package data.pojo

import data.Entity
import data.enums.FirestoreCollections

data class Wingman(
    val collection: FirestoreCollections = FirestoreCollections.WINGMAN,
    val id: String = "",
    val logo: String = "",
    val name: String = "",
) : Entity {
    fun getContentsPath() = "${collection.name}/$id/"
}