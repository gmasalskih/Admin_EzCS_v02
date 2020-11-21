package screens.maps_add

import androidx.compose.runtime.*
import screens.BaseController

class MapsAddController : BaseController<MapsAddViewState>() {
    override var _viewState: MapsAddViewState by mutableStateOf(MapsAddViewState())

    override fun onViewCreate() {
        println("onViewCreate MapsAddController")
    }

    override fun onViewDestroy() {
        println("onViewDestroy MapsAddController")
        clearViewState()
    }

    override fun clearViewState() {
        _viewState = MapsAddViewState()
    }
}