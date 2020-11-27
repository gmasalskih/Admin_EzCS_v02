package screens.danger_zone.menu

import androidx.compose.runtime.*
import router.NavigationTargets
import screens.BaseController

class DangerZoneMenuController : BaseController<DangerZoneMenuState>() {
    override var _state: DangerZoneMenuState by mutableStateOf(DangerZoneMenuState())

    fun navigateToAddDangerZoneRank() {
        router.navigateTo(NavigationTargets.DangerZoneAdd)
    }

    fun navigateToEditDangerZoneRank(id: String) {
        router.navigateTo(NavigationTargets.DangerZoneEdit(id))
    }
}