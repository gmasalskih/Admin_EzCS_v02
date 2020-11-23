package screens.danger_zone.edit

import androidx.compose.runtime.*
import screens.BaseController

class DangerZoneEditController:BaseController<DangerZoneEditState>() {
    override var _state: DangerZoneEditState by mutableStateOf(DangerZoneEditState())
}