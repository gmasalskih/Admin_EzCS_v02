package screens.wingman.menu

import androidx.compose.runtime.*
import screens.BaseController

class WingmanMenuController:BaseController<WingmanMenuState>() {
    override var _state: WingmanMenuState by mutableStateOf(WingmanMenuState())
}