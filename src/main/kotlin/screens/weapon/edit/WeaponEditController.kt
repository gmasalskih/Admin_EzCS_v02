package screens.weapon.edit

import androidx.compose.runtime.*
import data.entitys.Weapon
import screens.BaseController
import screens.ViewState

class WeaponEditController : BaseController<WeaponEditState>() {
    override var state: ViewState<WeaponEditState> by mutableStateOf(
        ViewState(
            title = "Edit weapon",
            item = WeaponEditState()
        )
    )
}