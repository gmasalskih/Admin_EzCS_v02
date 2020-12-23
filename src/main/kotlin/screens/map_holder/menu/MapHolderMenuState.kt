package screens.map_holder.menu

import data.types.ContentSourceType
import screens.State

data class MapHolderMenuState(
    val name: String = "",
    val documentName: String = "",
    val isCompetitive: Boolean = false,
    val logo: ContentSourceType = ContentSourceType.Empty,
    val map: ContentSourceType = ContentSourceType.Empty,
    val wallpaper: ContentSourceType = ContentSourceType.Empty,
) : State {
    override fun isValid(): Boolean = true
}