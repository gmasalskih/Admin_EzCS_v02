package screens.map_points.menu

import androidx.compose.runtime.*
import screens.BaseController

class MapPointsMenuController : BaseController<MapPointsMenuState>() {
    override var _state: MapPointsMenuState by mutableStateOf(MapPointsMenuState())
}