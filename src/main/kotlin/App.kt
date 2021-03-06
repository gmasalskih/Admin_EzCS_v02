import androidx.compose.desktop.Window
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntSize
import common_widgets.MenuApp
import di.*
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import router.Router
import ui.*

@KoinApiExtension
object App : KoinComponent {
    private val router: Router by inject()

    init {
        startKoin {
            modules(
                appModule,
                dependencyForProviderModule,
                providerModule,
                competitiveModule,
                dangerZoneModule,
                mapPointsModule,
                mapHolderModule,
                profileRankModule,
                weaponModule,
                wingmanModule,
                testModule
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
                }
            }
        }
    }
}








