package data.pojo

import data.types.EntityType
import data.types.GrenadeType
import data.types.TickrateType

data class MapPoint(
    override val name: String = "",
    override val entityType: EntityType = EntityType.MAP_POINT,
    val contentImages: List<String> = listOf(),
    val contentVideos: List<String> = listOf(),
    val grenadeType: GrenadeType = GrenadeType.SMOKE,
    val mapId: String = "",
    val previewEnd: String = "",
    val previewStart: String = "",
    val tickrateTypes: List<TickrateType> = listOf(),
) : Entity