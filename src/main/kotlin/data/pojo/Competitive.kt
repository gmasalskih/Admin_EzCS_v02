package data.pojo

import data.Entity
import data.enums.FirestoreCollections

data class Competitive(
    val id: String = "",
    val name: String = "",
    val logo: String = "",
    val collection: FirestoreCollections = FirestoreCollections.COMPETITIVE,
) : Entity {
    fun getContentsPath() = "${collection.name}/$id/"
}