package screens.competitive.add

import androidx.compose.runtime.*
import screens.BaseController
import screens.map_points.add.MapPointsAddState

class CompetitiveAddController : BaseController<CompetitiveAddState>() {
    override var _state: CompetitiveAddState by mutableStateOf(CompetitiveAddState())

    fun clearState(){
        _state = CompetitiveAddState()
    }
}