package screens.danger_zone.add

import androidx.compose.runtime.*
import screens.BaseController

class DangerZoneAddController:BaseController<DangerZoneAddState>() {
    override var _state: DangerZoneAddState by mutableStateOf(DangerZoneAddState())
}