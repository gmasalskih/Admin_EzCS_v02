package screens.main_menu

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import org.koin.core.inject
import providers.firebase.StorageProvider
import router.AppState
import router.Router
import screens.BaseController
import screens.ScreenState
import screens.maps.MapsView

class MainMenuController : BaseController<MenuViewState>() {
    private val storageProvider: StorageProvider by inject()
    val router by inject<Router>()
    override var viewState = MenuViewState()
    override var screenState: ScreenState by mutableStateOf(ScreenState.Data(viewState))

    fun goToMaps() {
        router.navigateTo(AppState.Maps)
    }

    fun changeData(){
        screenState = ScreenState.Data(viewState.copy(
            txt= "AAAAAAAAAAAAA"
        ))
    }
}