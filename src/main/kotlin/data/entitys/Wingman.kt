package data.entitys

import data.types.EntityType
import utils.ContentType
import utils.DataType

data class Wingman(
    @DataType
    override val name: String = "",

    @DataType
    override val entityType: EntityType = EntityType.WINGMAN,

    @ContentType
    val logo: String = "",
) : Entity