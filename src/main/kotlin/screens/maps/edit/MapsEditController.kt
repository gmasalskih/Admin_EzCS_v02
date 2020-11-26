package screens.maps.edit

import androidx.compose.runtime.*
import screens.BaseController

class MapsEditController : BaseController<MapsEditState>() {
    override var _state: MapsEditState by mutableStateOf(MapsEditState())

    override fun onViewCreate() {
        println("onViewCreate")
        _state = MapsEditState()
    }
    override fun onViewDestroy() {
        println("onViewDestroy")
        _state = MapsEditState()
    }
}