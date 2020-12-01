package data.pojo

import data.Entity
import data.enums.FirestoreCollections
import data.enums.GrenadeTypes
import data.enums.TickRate

data class MapPoint(
    val collection: FirestoreCollections = FirestoreCollections.MAP_POINTS,
    val contentImages: List<String> = listOf(),
    val contentVideos: List<String> = listOf(),
    val grenadeType: GrenadeTypes = GrenadeTypes.UNKNOWN,
    val id: String = "",
    val mapId: String = "",
    val name: String = "",
    val previewEnd: String = "",
    val previewStart: String = "",
    val tickRate: List<TickRate> = listOf(),
) : Entity {
    fun getContentsPath() = "${collection.name}/$id/"
}