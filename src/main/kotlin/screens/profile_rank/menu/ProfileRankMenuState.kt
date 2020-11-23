package screens.profile_rank.menu

import screens.TypeScreenState
import screens.ViewState

data class ProfileRankMenuState(
    override val typeScreenState: TypeScreenState = TypeScreenState.Data,
    override val title: String = "Profile Rank"
) : ViewState