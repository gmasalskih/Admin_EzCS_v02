package screens.competitive.add

import data.types.ContentSourceType
import screens.State

data class CompetitiveAddState(
    val name: String = "",
    val logo: ContentSourceType = ContentSourceType.Empty,
    val order: Int = 0,
) : State {
    override fun isValid() = name.isNotBlank() && logo !is ContentSourceType.Empty && order > 0
}