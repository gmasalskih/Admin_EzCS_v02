package screens.danger_zone.add

import screens.TypeScreenState
import screens.ViewState

data class DangerZoneAddState(
    override val typeScreenState: TypeScreenState = TypeScreenState.Data,
    override val title: String = "Add new competitive rank",
    val id: String = "",
    val name: String = "",
    val pathToLogo: String = ""
) : ViewState