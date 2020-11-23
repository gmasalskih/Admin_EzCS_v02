package screens.weapons.edit

import screens.TypeScreenState
import screens.ViewState

data class WeaponsEditState(
    override val typeScreenState: TypeScreenState = TypeScreenState.Data,
    override val title: String = ""
) : ViewState