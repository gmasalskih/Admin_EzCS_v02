package screens.map_points.add

import androidx.compose.runtime.*
import screens.BaseController

class MapPointsAddController : BaseController<MapPointsAddState>() {
    override var _state: MapPointsAddState by mutableStateOf(MapPointsAddState())

    fun clearState(){
        _state = MapPointsAddState()
    }
}