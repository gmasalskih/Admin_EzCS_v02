package screens.danger_zone.menu

import androidx.compose.runtime.*
import router.NavigationTargets
import screens.BaseController

class DangerZoneMenuController : BaseController<DangerZoneMenuState>() {
    override var _state: DangerZoneMenuState by mutableStateOf(DangerZoneMenuState())

    fun navigateToDangerZoneAdd() {
        router.navigateTo(NavigationTargets.DangerZoneAdd)
    }

    fun navigateToDangerZoneEdit(id: String) {
        router.navigateTo(NavigationTargets.DangerZoneEdit(id))
    }
}