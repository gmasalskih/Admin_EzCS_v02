package screens.competitive.edit

import data.types.EntityType
import screens.State
import utils.ContentType
import utils.DataType

data class CompetitiveEditState(
    @DataType
    override val name: String = "",

    @ContentType
    val logo: String = "",

    override val entityType: EntityType = EntityType.COMPETITIVE
) : State {
    override fun isValid(): Boolean = name.isNotBlank() && logo.isNotBlank()
}