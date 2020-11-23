package screens.competitive.add

import screens.TypeScreenState
import screens.ViewState

data class CompetitiveAddState(
    override val typeScreenState: TypeScreenState = TypeScreenState.Data,
    override val title: String = "Add new competitive rank",
    val rankId: String = "",
    val rankName: String = "",
    val pathToLogo: String = ""
):ViewState