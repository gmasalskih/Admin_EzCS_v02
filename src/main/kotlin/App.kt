import androidx.compose.desktop.Window
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import common_widgets.MainMenu
import di.appModule
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
                appModule
            )
        }
    }

    fun runApp(title: String) = Window(size = IntSize(1280, 800), title = title) {
        AdminEzCSTheme {
            //Background
            Box(
                modifier = Modifier.fillMaxSize(),
                backgroundColor = light
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








