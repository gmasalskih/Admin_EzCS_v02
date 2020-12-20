package screens.wingman.add

import data.types.EntityType
import screens.State
import utils.ContentType
import utils.DataType

data class WingmanAddState(
    val name: String = "",
    val logo: String = "",
    val order: String = "",
    override val entityType: EntityType = EntityType.WINGMAN,
) : State {
    override fun isValid() = name.isNotBlank() && logo.isNotBlank() && (order.toIntOrNull() ?: -1) > 0
}