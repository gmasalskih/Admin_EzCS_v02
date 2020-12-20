package screens.competitive.edit

import data.types.EntityType
import screens.State

data class CompetitiveEditState(
    val logo: String = "",
    override val entityType: EntityType = EntityType.COMPETITIVE
) : State {
    override fun isValid(): Boolean = logo.isNotBlank()
}