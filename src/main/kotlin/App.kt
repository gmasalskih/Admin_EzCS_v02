import androidx.compose.desktop.Window
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.size
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import di.appModule
import di.fbModules
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.inject
import router.Router
import screens.main_menu.MainMenuView


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

    fun runApp(title: String) {

        Window(
            size = IntSize(1280, 720),
            title = title
        ) {
            MaterialTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text(text = router.currentScreen.toString()) },
                            navigationIcon = {
                                if (router.currentScreen is MainMenuView) {
                                    IconButton(onClick = {}, enabled = false) {
                                        Image(
                                            asset = imageResource("icons/home.png"),
                                            alpha = 0.5f,
                                            modifier = Modifier.size(36.dp)
                                        )
                                    }
                                } else {
                                    IconButton(onClick = router::back) {
                                        Image(
                                            asset = imageResource("icons/back.png"),
                                        )
                                    }
                                }
                            },
                        )
                    }
                ) {
                    router.currentScreen.render()
                }
            }
        }

    }
}

