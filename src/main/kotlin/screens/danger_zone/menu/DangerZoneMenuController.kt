package screens.danger_zone.menu

import androidx.compose.runtime.*
import screens.BaseController

class DangerZoneMenuController:BaseController<DangerZoneMenuState>() {
    override var _state: DangerZoneMenuState by mutableStateOf(DangerZoneMenuState())
}