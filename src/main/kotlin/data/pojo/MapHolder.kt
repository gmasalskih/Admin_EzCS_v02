package data.pojo

import data.enums.FirestoreCollections

data class MapHolder(
    val collection: FirestoreCollections = FirestoreCollections.MAPS,
    val id: String,
    @field:JvmField
    val isCompetitive: Boolean = true,
    val logo: String,
    val map: String,
    val name: String,
    val wallpaper: String,
) {
    fun getContentsPath() = "${collection.name}/$id/"
}