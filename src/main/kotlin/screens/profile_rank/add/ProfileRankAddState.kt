package screens.profile_rank.add

import screens.TypeScreenState
import screens.ViewState

data class ProfileRankAddState(
    override val typeScreenState: TypeScreenState = TypeScreenState.Data,
    override val title: String = ""
) : ViewState