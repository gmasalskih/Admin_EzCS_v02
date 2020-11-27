package screens.map_holder.menu

import androidx.compose.runtime.*
import router.NavigationTargets
import screens.BaseController

class MapHolderMenuController : BaseController<MapHolderMenuState>() {
    override var _state: MapHolderMenuState by mutableStateOf(MapHolderMenuState())

    fun navigateToMapsAdd() {
        router.navigateTo(NavigationTargets.MapHolderAdd)
    }

    fun navigateToMapsEdit(mapId: String) {
        router.navigateTo(NavigationTargets.MapHolderEdit(mapId))
    }
}