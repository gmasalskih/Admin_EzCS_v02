package screens.map_holder.add

import screens.TypeScreenState
import screens.ViewState

data class MapHolderAddState(
    override val typeScreenState: TypeScreenState = TypeScreenState.Data,
    override val title: String = "Add Map",
    val id: String = "",
    val name: String = "",
    val pathToLogo: String = "",
    val pathToMap: String = "",
    val pathToWallpaper: String = "",
    val isCompetitive: Boolean = false
) : ViewState