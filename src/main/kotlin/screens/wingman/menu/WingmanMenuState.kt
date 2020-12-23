package screens.wingman.menu

import data.types.ContentSourceType
import screens.State

data class WingmanMenuState(
    val name: String = "",
    val documentName: String = "",
    val logo: ContentSourceType = ContentSourceType.Empty,
    val order: Int = 0,
) : State {
    override fun isValid(): Boolean = name.isNotBlank() && logo !is ContentSourceType.Empty && order > 0
}