package screens.wingman.edit

import screens.TypeScreenState
import screens.ViewState

data class WingmanEditState(
    override val typeScreenState: TypeScreenState = TypeScreenState.Data,
    override val title: String = "Edit rank",
    val id: String = "",
    val name: String = "",
    val pathToLogo: String = ""
) : ViewState