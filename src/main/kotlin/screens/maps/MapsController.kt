package screens.maps

import androidx.compose.runtime.*
import screens.BaseController

class MapsController : BaseController<MapsViewState>() {
    override var viewState: MapsViewState by mutableStateOf(MapsViewState())

    override fun onViewDestroy() {
    }
}