package screens.maps_add

import screens.TypeScreenState
import screens.ViewState

data class MapsAddViewState(
    override val typeScreenState: TypeScreenState = TypeScreenState.Data,
    override val title: String = "Add Map",
    val mapId: String = "",
    val mapName: String = "",
    val pathToLogo: String = "",
    val pathToMapImg: String = "",
    val pathToWallpaper: String = "",
    val isCompetitive: Boolean = false
) : ViewState