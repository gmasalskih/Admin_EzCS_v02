package data.pojo

import data.Entity
import data.enums.FirestoreCollections

data class ProfileRank(
    val collection: FirestoreCollections = FirestoreCollections.PROFILE_RANK,
    var xp: String = "",
    val id: String = "",
    var logo: String = "",
    val name: String = "",
) : Entity {
    fun getContentsPath() = "${collection.name}/$id/"
}