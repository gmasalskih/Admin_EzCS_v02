package screens.weapons.menu

import screens.TypeScreenState
import screens.ViewState

data class WeaponsMenuState(
    override val typeScreenState: TypeScreenState = TypeScreenState.Data,
    override val title: String = "Weapons"
) : ViewState