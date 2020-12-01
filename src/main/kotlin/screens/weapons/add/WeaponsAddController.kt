package screens.weapons.add

import androidx.compose.runtime.*
import data.pojo.Weapon
import screens.BaseController
import screens.ViewState

class WeaponsAddController : BaseController<Weapon>() {
    override var state: ViewState<Weapon> by mutableStateOf(ViewState(title = "Add new weapon", item = Weapon()))
}