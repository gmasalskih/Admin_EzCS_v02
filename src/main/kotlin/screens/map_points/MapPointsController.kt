package screens.map_points

import androidx.compose.runtime.*
import org.koin.core.inject
import router.Router
import screens.BaseController

class MapPointsController : BaseController<MapPointsViewState>() {
    override var viewState: MapPointsViewState by mutableStateOf(MapPointsViewState())
}