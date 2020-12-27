package screens.map_point.menu

import androidx.compose.runtime.*
import kotlinx.coroutines.launch
import router.NavigationTargets
import screens.BaseMenuController
import screens.ViewState

class MapPointMenuController : BaseMenuController<MapPointMenuState>() {

    override val defaultItemState: MapPointMenuState = MapPointMenuState()

    override var state: ViewState<MapPointMenuState> by mutableStateOf(
        ViewState(
            title = "Map points",
            item = defaultItemState
        )
    )

    fun navigateToMapPointsAdd() {
        router.navigateTo(NavigationTargets.MapPointsAdd)
    }

    fun navigateToMapPointsEdit(id: String) {
        router.navigateTo(NavigationTargets.MapPointsEdit(id))
    }

    override suspend fun setEntity() {
//        TODO("Not yet implemented")
    }

}