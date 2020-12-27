package screens.profile_rank.menu

import data.entitys.ProfileRank
import screens.State

data class ProfileRankMenuState(
    val listProfileRank: List<ProfileRank> = listOf()
) : State {
    override fun isValid(): Boolean = true
}