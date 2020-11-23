package screens.competitive.menu

import screens.TypeScreenState
import screens.ViewState

data class CompetitiveMenuState(
    override val typeScreenState: TypeScreenState = TypeScreenState.Data,
    override val title: String = "Competitive"
) : ViewState