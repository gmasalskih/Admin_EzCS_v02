package screens.profile_rank.edit

import screens.State

data class ProfileRankEditState(
    var xp: String = "",
    var logo: String = "",
) : State {
    override fun isValid(): Boolean = xp.isNotBlank() && logo.isNotBlank()
}