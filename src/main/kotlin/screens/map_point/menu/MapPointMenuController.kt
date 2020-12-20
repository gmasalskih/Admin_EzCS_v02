package screens.map_point.menu

import androidx.compose.runtime.*
import data.entitys.MapHolder
import router.NavigationTargets
import screens.BaseController
import screens.ViewState

class MapPointMenuController : BaseController<List<MapHolder>>() {
    override var state: ViewState<List<MapHolder>> by mutableStateOf(
        ViewState(
            title = "Map points",
            item = listOf()
        )
    )

    fun navigateToMapPointsAdd() {
        router.navigateTo(NavigationTargets.MapPointsAdd)
    }

    fun navigateToMapPointsEdit(id: String) {
        router.navigateTo(NavigationTargets.MapPointsEdit(id))
    }

    override fun initState() {
//        TODO("Not yet implemented")
    }
}