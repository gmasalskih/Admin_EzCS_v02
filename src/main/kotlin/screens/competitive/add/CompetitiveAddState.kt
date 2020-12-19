package screens.competitive.add

import data.types.EntityType
import screens.State
import utils.ContentType
import utils.DataType

data class CompetitiveAddState(
    @DataType
    override val name: String = "",

    @ContentType
    val logo: String = "",

    @DataType
    val order: Int = -1,

    override val entityType: EntityType = EntityType.COMPETITIVE
) : State {
    override fun isValid() = name.isNotBlank() && logo.isNotBlank() && order > 0
}