package screens.wingman.add

import data.types.ContentSourceType
import screens.ItemViewState

data class WingmanAddItemViewState(
    val name: String = "",
    val logo: ContentSourceType = ContentSourceType.Empty,
    val order: Int = 0,
) : ItemViewState {
    override fun isValid() = name.isNotBlank() && logo !is ContentSourceType.Empty && order > 0
}