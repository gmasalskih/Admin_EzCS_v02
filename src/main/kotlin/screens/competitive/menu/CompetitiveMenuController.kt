package screens.competitive.menu

import androidx.compose.runtime.*
import screens.BaseController

class CompetitiveMenuController : BaseController<CompetitiveMenuState>() {
    override var _state: CompetitiveMenuState by mutableStateOf(CompetitiveMenuState())
}