package screens.map_holder.menu

import data.types.ContentSourceType
import screens.State

data class MapHolderMenuState(
    val documentName: String,
    val name: String,
    val isCompetitive: Boolean,
    val logo: ContentSourceType = ContentSourceType.Empty,
    val map: ContentSourceType = ContentSourceType.Empty,
    val wallpaper: ContentSourceType = ContentSourceType.Empty,
) : State {
    override fun isValid(): Boolean = true
}