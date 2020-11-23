package screens.competitive.add

import androidx.compose.runtime.*
import screens.BaseController

class CompetitiveAddController : BaseController<CompetitiveAddState>() {
    override var _state: CompetitiveAddState by mutableStateOf(CompetitiveAddState())
}