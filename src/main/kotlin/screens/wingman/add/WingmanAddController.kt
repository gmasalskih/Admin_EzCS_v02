package screens.wingman.add

import androidx.compose.runtime.*
import screens.BaseController

class WingmanAddController : BaseController<WingmanAddState>() {
    override var _state: WingmanAddState by mutableStateOf(WingmanAddState())

    fun clearState(){
        _state = WingmanAddState()
    }
}