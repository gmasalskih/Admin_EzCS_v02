package screens.map_points.menu

import androidx.compose.runtime.*
import router.NavigationTargets
import screens.BaseController

class MapPointsMenuController : BaseController<MapPointsMenuState>() {
    override var _state: MapPointsMenuState by mutableStateOf(MapPointsMenuState())

    fun navigateToAddMapPoint() {
        router.navigateTo(NavigationTargets.MapPointsAdd)
    }

    fun navigateToEditMapPoint(id: String) {
        router.navigateTo(NavigationTargets.MapPointsEdit(id))
    }
}