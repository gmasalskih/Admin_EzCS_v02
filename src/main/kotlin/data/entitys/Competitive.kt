package data.entitys

import data.types.EntityType
import utils.ContentType
import utils.DataType

data class Competitive(
    @DataType
    override val name: String = "",

    @DataType
    override val entityType: EntityType = EntityType.COMPETITIVE,

    @ContentType
    val logo: String = "",

    @DataType
    val order: Int = 0
) : Entity