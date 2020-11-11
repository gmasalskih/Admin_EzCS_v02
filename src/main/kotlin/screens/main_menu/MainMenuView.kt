package screens.main_menu

import androidx.compose.foundation.Box
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.koin.core.inject
import router.AppState
import screens.BaseView

class MainMenuView : BaseView<MenuViewState, MainMenuController>() {
    override val controller: MainMenuController by inject()

    @Composable
    override fun loading() {
        MainMenuLoading()
    }

    @Composable
    override fun error(e: Exception) {
        MainMenuError(e)
    }

    @Composable
    override fun data(viewState: MenuViewState) {
        MainMenuData(controller, viewState)
    }
}

@Composable
private fun MainMenuData(controller: MainMenuController, viewState: MenuViewState) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text("MainMenuView")
        Button(onClick = controller::goToMaps) {
            Text("Go to Maps")
        }
        Button(onClick = controller::changeData) {
            Text(viewState.txt)
        }
    }
}

@Composable
fun MainMenuLoading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        gravity = Alignment.Center
    ) {
        Text("Loading")
    }
}

@Composable
private fun MainMenuError(e: Exception) {
    Box(
        modifier = Modifier.fillMaxSize(),
        gravity = Alignment.Center
    ) {
        Text("${e.message}")
    }
}