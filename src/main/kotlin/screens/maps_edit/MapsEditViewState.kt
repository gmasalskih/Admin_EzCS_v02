package screens.maps_edit

import screens.TypeScreenState
import screens.ViewState

data class MapsEditViewState(
    override val typeScreenState: TypeScreenState = TypeScreenState.Data,
    val mapId: String = "",
    val mapName: String = "",
    override val title: String = "Edit map",
    val pathToLogo: String = "",
    val pathToMapImg: String = "",
    val pathToWallpaper: String = "",
    val isCompetitive: Boolean = false
) : ViewState