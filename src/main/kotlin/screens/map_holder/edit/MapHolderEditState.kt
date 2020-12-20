package screens.map_holder.edit

import screens.State

data class MapHolderEditState(
    val logo: String = "",
    val map: String = "",
    val wallpaper: String = "",
    val isCompetitive: Boolean = false,
) : State {
    override fun isValid() = logo.isNotBlank() && map.isNotBlank() && wallpaper.isNotBlank()
}