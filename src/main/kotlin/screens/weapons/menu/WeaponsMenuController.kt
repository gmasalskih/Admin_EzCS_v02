package screens.weapons.menu

import androidx.compose.runtime.*
import screens.BaseController

class WeaponsMenuController : BaseController<WeaponsMenuState>() {
    override var _state: WeaponsMenuState by mutableStateOf(WeaponsMenuState())
}