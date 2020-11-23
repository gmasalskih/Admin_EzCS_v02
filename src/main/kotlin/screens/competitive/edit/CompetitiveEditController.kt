package screens.competitive.edit

import androidx.compose.runtime.*
import screens.BaseController

class CompetitiveEditController:BaseController<CompetitiveEditState>() {
    override var _state: CompetitiveEditState by mutableStateOf(CompetitiveEditState())
}