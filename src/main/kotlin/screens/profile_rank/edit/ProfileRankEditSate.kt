package screens.profile_rank.edit

import screens.TypeScreenState
import screens.ViewState

data class ProfileRankEditSate(
    override val typeScreenState: TypeScreenState = TypeScreenState.Data,
    override val title: String = "Edit profile rank",
    val id: String = "",
    val name: String = "",
    val pathToLogo: String = "",
    val xp: String = "",
) : ViewState