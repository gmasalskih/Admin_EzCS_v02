package screens.maps.menu

import androidx.compose.runtime.*
import router.NavigationTargets
import screens.BaseController

class MapsMenuController : BaseController<MapsMenuState>() {
    override var _state: MapsMenuState by mutableStateOf(MapsMenuState())

    fun navigateToAddMap() {
        router.navigateTo(NavigationTargets.MapsAdd)
    }

    fun navigateToEditMap(mapId: String) {
        router.navigateTo(NavigationTargets.MapsEdit(mapId))
    }
}