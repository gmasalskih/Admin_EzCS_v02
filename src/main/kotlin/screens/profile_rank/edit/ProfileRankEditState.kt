package screens.profile_rank.edit

import data.types.ContentSourceType
import screens.State

data class ProfileRankEditState(
    val name: String = "",
    val xp: Int = 0,
    val logo: ContentSourceType = ContentSourceType.Empty,
    val order: Int = 0
) : State {
    override fun isValid(): Boolean = name.isNotBlank() && xp > 0 && logo !is ContentSourceType.Empty && order > 0
}