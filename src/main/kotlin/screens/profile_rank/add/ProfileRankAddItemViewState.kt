package screens.profile_rank.add

import data.types.ContentSourceType
import screens.ItemViewState

data class ProfileRankAddItemViewState(
    val name: String = "",
    val xp: Int = 0,
    val logo: ContentSourceType = ContentSourceType.Empty,
    val order: Int = 0,
) : ItemViewState {
    override fun isValid(): Boolean = name.isNotBlank() && xp > 0 && logo !is ContentSourceType.Empty && order > 0
}