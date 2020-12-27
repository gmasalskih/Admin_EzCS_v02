package screens.map_holder.menu

import data.entitys.MapHolder
import data.types.ContentSourceType
import screens.State

data class MapHolderMenuState(
    val listMapHolder: List<MapHolder> = listOf(),
) : State {
    override fun isValid(): Boolean = true
}