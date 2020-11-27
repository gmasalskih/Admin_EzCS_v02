package screens.competitive.edit

import screens.TypeScreenState
import screens.ViewState

data class CompetitiveEditState(
    override val typeScreenState: TypeScreenState = TypeScreenState.Data,
    override val title: String = "Edit rank",
    val id: String = "",
    val name: String = "",
    val pathToLogo: String = ""
) : ViewState