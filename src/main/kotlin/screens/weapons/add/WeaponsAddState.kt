package screens.weapons.add

import screens.TypeScreenState
import screens.ViewState

data class WeaponsAddState(
    override val typeScreenState: TypeScreenState = TypeScreenState.Data,
    override val title: String = ""
) : ViewState