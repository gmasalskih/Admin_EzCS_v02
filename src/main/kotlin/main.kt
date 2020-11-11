import androidx.compose.desktop.AppWindowAmbient
import androidx.compose.desktop.ComposeWindow
import androidx.compose.desktop.Window
import androidx.compose.desktop.setContent
import androidx.compose.foundation.Text
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import di.appModule
import di.fbModules
import org.koin.core.context.startKoin
import org.koin.ext.scope
import router.AppState
import router.Router
import screens.main_menu.*
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
}




