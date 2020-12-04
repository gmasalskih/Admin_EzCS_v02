package data.entitys

import data.types.EntityType

data class Competitive(
    override val name: String = "",
    override val entityType: EntityType = EntityType.COMPETITIVE,
    val logo: String = "",
) : Entity