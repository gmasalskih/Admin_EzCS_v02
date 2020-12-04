package screens.wingman.add

import data.types.EntityType

data class WingmanAddState(
    val name: String = "",
    val entityType: EntityType = EntityType.WINGMAN,
    val logo: String = "",
)