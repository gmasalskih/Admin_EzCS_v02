package screens.weapons.add

import androidx.compose.runtime.*
import screens.BaseController

class WeaponsAddController:BaseController<WeaponsAddState>() {
    override var _state: WeaponsAddState by mutableStateOf(WeaponsAddState())
}