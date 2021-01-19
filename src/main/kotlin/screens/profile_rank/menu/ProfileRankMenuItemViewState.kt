package screens.profile_rank.menu

import data.entitys.ProfileRank
import screens.ItemViewState

data class ProfileRankMenuItemViewState(
    val listProfileRank: List<ProfileRank> = listOf()
) : ItemViewState {
    override fun isValid(): Boolean = true
}