package screens.map_holder.add

import data.types.ContentSourceType
import screens.ItemViewState

data class MapHolderAddItemViewState(
    val name: String = "",
    val isCompetitive: Boolean = false,
    val logo: ContentSourceType = ContentSourceType.Empty,
    val map: ContentSourceType = ContentSourceType.Empty,
    val wallpaper: ContentSourceType = ContentSourceType.Empty,
) : ItemViewState {
    override fun isValid() = name.isNotBlank() &&
            logo !is ContentSourceType.Empty &&
            map !is ContentSourceType.Empty &&
            wallpaper !is ContentSourceType.Empty
}