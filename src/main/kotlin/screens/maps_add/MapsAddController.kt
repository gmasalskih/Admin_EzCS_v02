package screens.maps_add

import androidx.compose.runtime.*
import screens.BaseController

class MapsAddController : BaseController<MapsAddViewState>() {
    override var viewState: MapsAddViewState by mutableStateOf(MapsAddViewState())



    override fun onViewDestroy() {
        viewState = MapsAddViewState()
    }
}