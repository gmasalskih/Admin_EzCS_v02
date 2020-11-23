package screens.danger_zone.edit

import screens.TypeScreenState
import screens.ViewState

data class DangerZoneEditState(
    override val typeScreenState: TypeScreenState = TypeScreenState.Data,
    override val title: String = ""
) : ViewState