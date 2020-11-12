import androidx.compose.desktop.Window
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.unit.IntSize
import common_widgets.EntryPoint
import di.appModule
import di.fbModules
import org.koin.core.context.startKoin
import router.AppState
import router.Router
import screens.main_menu.MainMenuView
import screens.maps.MapsView

fun main() {
    startKoin {
        modules(
            fbModules,
            appModule
        )
    }
    Router.addView(AppState.MainMenu, MainMenuView())
        .addView(AppState.Maps, MapsView())
        .init()
    EntryPoint()
}




