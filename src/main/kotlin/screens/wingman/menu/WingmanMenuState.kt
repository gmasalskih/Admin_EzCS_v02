package screens.wingman.menu

import screens.TypeScreenState
import screens.ViewState

data class WingmanMenuState(
    override val typeScreenState: TypeScreenState = TypeScreenState.Data,
    override val title: String = "Wingman"
) : ViewState