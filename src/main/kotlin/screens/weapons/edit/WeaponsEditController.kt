package screens.weapons.edit

import androidx.compose.runtime.*
import screens.BaseController

class WeaponsEditController : BaseController<WeaponsEditState>() {
    override var _state: WeaponsEditState by mutableStateOf(WeaponsEditState())
}