package screens.competitive.add

import data.types.EntityType
import screens.State

data class CompetitiveAddState(
    override val name: String = "",
    val logo: String = "",
    override val entityType: EntityType = EntityType.COMPETITIVE
) : State {
    override fun isValid(): Boolean = name.isNotBlank() && logo.isNotBlank()
}