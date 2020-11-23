package screens.map_points.menu

import data.pojo.MapHolder
import screens.TypeScreenState
import screens.ViewState

data class MapPointsMenuState(
    override val typeScreenState: TypeScreenState = TypeScreenState.Data,
    override val title: String = "Map Points",
    val listMapHolders: List<MapHolder> = listOf()
) : ViewState