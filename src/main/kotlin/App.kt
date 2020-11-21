import androidx.compose.desktop.Window
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntSize
import common_widgets.MainMenu
import di.appModule
import di.controllersModule
import di.fbModules
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.inject
import router.Router
import ui.*

object App : KoinComponent {
    private val router: Router by inject()

    init {
        startKoin {
            modules(
                fbModules,
                appModule,
                controllersModule
            )
        }
    }

    fun runApp(title: String) = Window(size = IntSize(1280, 800), title = title) {
        AdminEzCSTheme {
            //Background
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = light
            ) {
                Row(
                    modifier = Modifier.fillMaxSize()
                ) {
                    //Menu
                    MainMenu(router)
                    // Content
                    router.currentScreen.render()
                }
            }
        }
    }
}








