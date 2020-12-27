package screens.map_point.edit

import data.types.EntityType
import data.types.GrenadeType
import data.types.TickrateType
import screens.State

data class MapPointEditState(
    val name: String = "",
    val entityType: EntityType = EntityType.MAP_POINT,
    val contentImages: List<String> = listOf(),
    val contentVideos: List<String> = listOf(),
    val grenadeType: GrenadeType = GrenadeType.SMOKE,
    val mapId: String = "",
    val previewEnd: String = "",
    val previewStart: String = "",
    val tickrateTypes: List<TickrateType> = listOf(),
):State {
    override fun isValid(): Boolean {
//        TODO("Not yet implemented")
        return false
    }
}
