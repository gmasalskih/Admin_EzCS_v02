package data.entitys

import data.types.EntityType
import data.types.GrenadeType
import data.types.TickrateType
import utils.ContentType
import utils.DataType

data class MapPoint(
    @DataType
    override val name: String = "",

    @DataType
    override val entityType: EntityType = EntityType.MAP_POINT,

    @ContentType
    val contentImages: List<String> = listOf(),

    @ContentType
    val contentVideos: List<String> = listOf(),

    @DataType
    val grenadeType: GrenadeType = GrenadeType.SMOKE,

    @DataType
    val mapId: String = "",

    @ContentType
    val previewEnd: String = "",

    @ContentType
    val previewStart: String = "",

    @DataType
    val tickrateTypes: List<TickrateType> = listOf(),
) : Entity