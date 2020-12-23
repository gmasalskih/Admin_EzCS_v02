package screens.profile_rank.menu

import data.types.ContentSourceType
import screens.State

data class ProfileRankMenuState(
    val name: String = "",
    val documentName: String = "",
    val xp: Int = 0,
    val logo: ContentSourceType = ContentSourceType.Empty,
    val order: Int = 0
) : State {
    override fun isValid(): Boolean =
        name.isNotBlank() && documentName.isNotBlank() && xp > 0 && logo !is ContentSourceType.Empty && order > 0
}