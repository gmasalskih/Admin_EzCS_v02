package screens.map_points

import androidx.compose.runtime.*
import screens.BaseController

class MapPointsController : BaseController<MapPointsViewState>() {
    override var viewState: MapPointsViewState by mutableStateOf(MapPointsViewState())

    override fun onViewDestroy() {
    }
}