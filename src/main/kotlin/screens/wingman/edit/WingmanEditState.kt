package screens.wingman.edit

import data.types.EntityType
import screens.State

data class WingmanEditState(
    val logo: String = "",
    override val entityType: EntityType = EntityType.WINGMAN,
) : State {
    override fun isValid(): Boolean = logo.isNotBlank()
}