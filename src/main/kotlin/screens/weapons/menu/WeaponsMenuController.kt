package screens.weapons.menu

import androidx.compose.runtime.*
import data.pojo.Weapon
import screens.BaseController
import screens.ViewState

class WeaponsMenuController : BaseController<List<Weapon>>() {
    override var state: ViewState<List<Weapon>> by mutableStateOf(
        ViewState(
            title = "Weapons",
            item = listOf()
        )
    )
}