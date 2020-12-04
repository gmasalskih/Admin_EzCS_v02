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
            item = listOf(
                MapHolder(
                    name = "Dust II",
                    logo = "",
                    wallpaper = "",
                    map = ""
                ),
                MapHolder(
                    name = "Mirage",
                    logo = "",
                    wallpaper = "",
                    map = ""
                ),
                MapHolder(
                    name = "Inferno",
                    logo = "",
                    wallpaper = "",
                    map = ""
                ),
            )
        )
    )

    fun navigateToMapPointsAdd() {
        router.navigateTo(NavigationTargets.MapPointsAdd)
    }

    fun navigateToMapPointsEdit(id: String) {
        router.navigateTo(NavigationTargets.MapPointsEdit(id))
    }
}