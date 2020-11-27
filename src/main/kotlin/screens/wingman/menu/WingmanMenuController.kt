package screens.wingman.menu

import androidx.compose.runtime.*
import router.NavigationTargets
import screens.BaseController

class WingmanMenuController : BaseController<WingmanMenuState>() {
    override var _state: WingmanMenuState by mutableStateOf(WingmanMenuState())

    fun navigateToAddWingmanRank() {
        router.navigateTo(NavigationTargets.WingmanAdd)
    }

    fun navigateToEditWingmanRankRank(id: String) {
        router.navigateTo(NavigationTargets.WingmanEdit(id))
    }
}