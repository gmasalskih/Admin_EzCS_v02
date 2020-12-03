package data.pojo

import data.types.EntityType

data class Wingman(
    override val name: String = "",
    override val entityType: EntityType = EntityType.WINGMAN,
    val logo: String = "",
) : Entity