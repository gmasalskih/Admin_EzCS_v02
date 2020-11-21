package screens.maps

import androidx.compose.runtime.*
import router.NavigationTargets
import screens.BaseController

class MapsController : BaseController<MapsViewState>() {
    override var _viewState: MapsViewState by mutableStateOf(MapsViewState())

    fun navigateToAddMap(){
        router.navigateTo(NavigationTargets.MapsAdd)
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