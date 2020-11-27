package screens.wingman.menu

import androidx.compose.runtime.*
import router.NavigationTargets
import screens.BaseController

class WingmanMenuController : BaseController<WingmanMenuState>() {
    override var _state: WingmanMenuState by mutableStateOf(WingmanMenuState())

    fun navigateToWingmanAdd() {
        router.navigateTo(NavigationTargets.WingmanAdd)
    }

    fun navigateToWingmanEdit(id: String) {
        router.navigateTo(NavigationTargets.WingmanEdit(id))
    }
}