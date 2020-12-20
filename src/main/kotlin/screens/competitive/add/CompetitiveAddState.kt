package screens.competitive.add

import data.types.EntityType
import screens.State

data class CompetitiveAddState(
    val name: String = "",
    val logo: String = "",
    val order: String = "",
    override val entityType: EntityType = EntityType.COMPETITIVE
) : State {
    override fun isValid() = name.isNotBlank() && logo.isNotBlank() && (order.toIntOrNull() ?: -1) > 0
}