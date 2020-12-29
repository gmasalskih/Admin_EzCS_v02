package data.entitys

import data.types.EntityType
import data.types.GrenadeType
import data.types.TickrateType
import utils.ContentType
import utils.DataType
import java.lang.StringBuilder

data class MapPoint(
    @DataType
    override val entityType: EntityType = EntityType.MAP_POINT,

    @DataType
    override val name: String = "",

    @DataType
    val mapDocumentName: String = "",

    @DataType
    val grenadeType: GrenadeType = GrenadeType.SMOKE,

    @DataType
    val tickrateTypes: List<TickrateType> = listOf(),

    @ContentType
    val previewStart: String = "",

    @ContentType
    val previewEnd: String = "",

    @ContentType
    val contentImages: List<String> = listOf(),

    @ContentType
    val contentVideos: List<String> = listOf(),
) : Entity {
    override fun getDocumentName(): String = StringBuilder().apply {
        append(entityType.name)
        append("/")
        append(mapDocumentName.substringAfterLast("/").toLowerCase())
        append("_")
        append(grenadeType.name.toLowerCase())
        append("_")
        tickrateTypes.forEach {
            append(it.value)
            append("_")
        }
        append(
            name.replace("[^a-zA-Z0-9_\\s]".toRegex(), "")
                .replace("\\s".toRegex(), "_")
                .replace("[_]+".toRegex(), "_")
                .toLowerCase()
        )
    }.toString()
}