package screens.wingman.edit

import data.types.EntityType

data class WingmanEditState(
    val name: String = "",
    val entityType: EntityType = EntityType.WINGMAN,
    val logo: String = "",
)