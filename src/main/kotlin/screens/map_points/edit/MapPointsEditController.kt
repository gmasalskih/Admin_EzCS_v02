package screens.map_points.edit

import androidx.compose.runtime.*
import screens.BaseController

class MapPointsEditController : BaseController<MapPointsEditState>() {
    override var _state: MapPointsEditState by mutableStateOf(MapPointsEditState())
}