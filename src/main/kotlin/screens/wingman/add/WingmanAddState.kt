package screens.wingman.add

import screens.TypeScreenState
import screens.ViewState

data class WingmanAddState(
    override val typeScreenState: TypeScreenState = TypeScreenState.Data,
    override val title: String = "Add new wingman rank",
    val id: String = "",
    val name: String = "",
    val pathToLogo: String = ""
) : ViewState