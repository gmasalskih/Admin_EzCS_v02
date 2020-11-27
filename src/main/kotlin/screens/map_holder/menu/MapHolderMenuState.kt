package screens.map_holder.menu

import data.pojo.MapHolder
import screens.TypeScreenState
import screens.ViewState

data class MapHolderMenuState(
    override val typeScreenState: TypeScreenState = TypeScreenState.Data,
    override val title: String = "Maps",
    val listMapHolders: List<MapHolder> = listOf()
) : ViewState