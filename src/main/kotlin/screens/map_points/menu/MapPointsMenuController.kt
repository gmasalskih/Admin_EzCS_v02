package screens.map_points.menu

import androidx.compose.runtime.*
import router.NavigationTargets
import screens.BaseController

class MapPointsMenuController : BaseController<MapPointsMenuState>() {
    override var _state: MapPointsMenuState by mutableStateOf(MapPointsMenuState())

    fun navigateToMapPointsAdd() {
        router.navigateTo(NavigationTargets.MapPointsAdd)
    }

    fun navigateToMapPointsEdit(id: String) {
        router.navigateTo(NavigationTargets.MapPointsEdit(id))
    }
}