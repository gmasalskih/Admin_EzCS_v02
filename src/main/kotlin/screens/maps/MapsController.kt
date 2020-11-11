package screens.maps

import androidx.compose.runtime.*
import org.koin.core.inject
import router.AppState
import router.Router
import screens.BaseController
import screens.ScreenState
import screens.main_menu.MainMenuView

class MapsController : BaseController<MapsViewState>() {
    val router by inject<Router>()
    override var viewState: MapsViewState = MapsViewState()
    override var screenState: ScreenState by mutableStateOf(ScreenState.Data(viewState))

    fun buttonClick() {
        router.navigateTo(AppState.MainMenu)
    }
}