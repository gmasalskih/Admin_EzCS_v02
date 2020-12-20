package screens.map_holder.edit

import data.types.EntityType
import screens.State

data class MapHolderEditState(
    val logo: String = "",
    val map: String = "",
    val wallpaper: String = "",
    val isCompetitive: Boolean = false,
    override val entityType: EntityType = EntityType.MAP_HOLDER
) : State {
    override fun isValid() = logo.isNotBlank() && map.isNotBlank() && wallpaper.isNotBlank()
}