package screens.competitive.menu

import data.types.ContentSourceType
import screens.State

data class CompetitiveMenuState(
    val name: String = "",
    val documentName: String = "",
    val logo: ContentSourceType = ContentSourceType.Empty,
    val order: Int = 0
) : State {
    override fun isValid(): Boolean =
        name.isNotEmpty() && documentName.isNotEmpty() && logo !is ContentSourceType.Empty && order > 0
}