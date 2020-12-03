package screens.weapon.edit

import androidx.compose.runtime.*
import data.pojo.Weapon
import screens.BaseController
import screens.ViewState

class WeaponEditController : BaseController<Weapon>() {
    override var state: ViewState<Weapon> by mutableStateOf(ViewState(title = "Edit weapon", item = Weapon()))
}