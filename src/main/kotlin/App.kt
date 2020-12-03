import androidx.compose.desktop.Window
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntSize
import common_widgets.MenuApp
import di.*
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
                appModule,
                dropboxModule,
                fbModules,
                competitiveModule,
                dangerZoneModule,
                mapPointsModule,
                mapsModule,
                profileRankModule,
                weaponsModule,
                wingmanModule
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
                    MenuApp(router)
                    // Content
                    router.currentScreen.render()
//                    router.currentScreen.render()
                }
            }
        }
    }
}








