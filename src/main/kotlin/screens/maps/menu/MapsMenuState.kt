package screens.maps.menu

import data.pojo.MapHolder
import screens.TypeScreenState
import screens.ViewState

data class MapsMenuState(
    override val typeScreenState: TypeScreenState = TypeScreenState.Data,
    override val title: String = "Maps",
    val listMapHolders: List<MapHolder> = listOf()
) : ViewState