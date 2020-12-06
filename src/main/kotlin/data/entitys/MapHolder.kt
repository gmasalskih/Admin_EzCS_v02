package data.entitys

import data.types.EntityType
import utils.ContentType
import utils.DataType

data class MapHolder(
    @DataType
    override val name: String = "",

    @DataType
    override val entityType: EntityType = EntityType.MAP_HOLDER,

    @field:JvmField
    @DataType
    val isCompetitive: Boolean = true,

    @ContentType
    val logo: String = "",

    @ContentType
    val map: String = "",

    @ContentType
    val wallpaper: String = "",
) : Entity