package screens.maps

import androidx.compose.runtime.*
import router.NavigationTargets
import screens.BaseController

class MapsController : BaseController<MapsViewState>() {
    override var _viewState: MapsViewState by mutableStateOf(MapsViewState())

    fun navigateToAddMap() {
        router.navigateTo(NavigationTargets.MapsAdd)
    }

    fun navigateToEditMap(mapId: String) {
        router.navigateTo(NavigationTargets.MapsEdit(mapId))
    }

    override fun onViewCreate() {
        println("onViewCreate MapsController")
    }

    override fun onViewDestroy() {
        println("onViewDestroy MapsController")
    }

    override fun clearViewState() {
        _viewState = MapsViewState()
    }
}