package screens.map_holder.add

import data.types.EntityType
import screens.State

data class MapHolderAddState(
    val name: String = "",
    override val entityType: EntityType = EntityType.MAP_HOLDER,
    val logo: String = "",
    val map: String = "",
    val wallpaper: String = "",
    val isCompetitive: Boolean = false,
) : State {
    override fun isValid() = name.isNotBlank() && logo.isNotBlank() && map.isNotBlank() && wallpaper.isNotBlank()
}