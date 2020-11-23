package screens.wingman.edit

import androidx.compose.runtime.*
import screens.BaseController

class WingmanEditController : BaseController<WingmanEditState>() {
    override var _state: WingmanEditState by mutableStateOf(WingmanEditState())
}