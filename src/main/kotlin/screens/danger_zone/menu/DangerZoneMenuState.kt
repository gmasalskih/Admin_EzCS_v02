package screens.danger_zone.menu

import screens.TypeScreenState
import screens.ViewState

data class DangerZoneMenuState(
    override val typeScreenState: TypeScreenState = TypeScreenState.Data,
    override val title: String = "Danger Zone"
) : ViewState