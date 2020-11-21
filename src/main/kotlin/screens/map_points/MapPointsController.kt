package screens.map_points

import androidx.compose.runtime.*
import screens.BaseController

class MapPointsController : BaseController<MapPointsViewState>() {
    override var _viewState: MapPointsViewState by mutableStateOf(MapPointsViewState())

    override fun onViewCreate() {
        println("onViewCreate MapPointsController")
    }

    override fun onViewDestroy() {
        println("onViewDestroy MapPointsController")
    }

    override fun clearViewState() {
        _viewState = MapPointsViewState()
    }


}