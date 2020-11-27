package screens.map_holder.edit

import screens.TypeScreenState
import screens.ViewState

data class MapHolderEditState(
    override val typeScreenState: TypeScreenState = TypeScreenState.Data,
    override val title: String = "Edit map",
    val id: String = "",
    val name: String = "",
    val pathToLogo: String = "",
    val pathToMap: String = "",
    val pathToWallpaper: String = "",
    val isCompetitive: Boolean = false
) : ViewState