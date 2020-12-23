package screens.competitive.edit

import data.types.ContentSourceType
import screens.State

data class CompetitiveEditState(
    val name: String = "",
    val logo: ContentSourceType = ContentSourceType.Empty,
    val order: Int = 0
) : State {
    override fun isValid(): Boolean =
        name.isNotBlank() && logo !is ContentSourceType.Empty && order > 0
}