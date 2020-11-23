package screens.maps.add

import androidx.compose.runtime.*
import screens.BaseController

class MapsAddController : BaseController<MapsAddState>() {
    override var _state: MapsAddState by mutableStateOf(MapsAddState())

    override fun onViewDestroy() {
        clearViewState()
    }

    fun clearViewState() {
        _state = MapsAddState()
    }
}