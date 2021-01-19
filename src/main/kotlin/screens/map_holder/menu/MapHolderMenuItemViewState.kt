package screens.map_holder.menu

import data.entitys.MapHolder
import screens.ItemViewState

data class MapHolderMenuItemViewState(
    val listMapHolder: List<MapHolder> = listOf(),
) : ItemViewState {
    override fun isValid(): Boolean = true
}