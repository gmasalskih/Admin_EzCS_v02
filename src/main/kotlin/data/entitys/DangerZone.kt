package data.entitys

import data.types.EntityType
import utils.ContentType
import utils.DataType

data class DangerZone(
    @DataType
    override val name: String = "",

    @DataType
    override val entityType: EntityType = EntityType.DANGER_ZONE,

    @ContentType
    val logo: String = "",
) : Entity