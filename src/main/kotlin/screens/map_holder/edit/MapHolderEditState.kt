package screens.map_holder.edit

import data.types.ContentSourceType
import screens.State

data class MapHolderEditState(
    val name: String = "",
    val isCompetitive: Boolean = false,
    val logo: ContentSourceType = ContentSourceType.Empty,
    val map: ContentSourceType = ContentSourceType.Empty,
    val wallpaper: ContentSourceType = ContentSourceType.Empty,
) : State {
    override fun isValid() = name.isNotBlank() &&
            logo !is ContentSourceType.Empty &&
            map !is ContentSourceType.Empty &&
            wallpaper !is ContentSourceType.Empty
}