package screens.map_holder.add

import androidx.compose.runtime.*
import screens.BaseController

class MapHolderController : BaseController<MapHolderAddState>() {
    override var _state: MapHolderAddState by mutableStateOf(MapHolderAddState())

    fun clearState() {
        _state = MapHolderAddState()
    }
}