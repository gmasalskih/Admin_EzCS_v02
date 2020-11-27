package screens.profile_rank.add

import screens.TypeScreenState
import screens.ViewState

data class ProfileRankAddState(
    override val typeScreenState: TypeScreenState = TypeScreenState.Data,
    override val title: String = "Add new profile rank",
    val rankId: String = "",
    val rankName: String = "",
    val pathToLogo: String = "",
    val rankXP:String = "",
) : ViewState