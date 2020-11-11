package screens.maps

import androidx.compose.foundation.Box
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.koin.core.inject
import screens.BaseView

class MapsView : BaseView<MapsViewState, MapsController>() {
    override val controller: MapsController by inject()

    @Composable
    override fun loading() {

    }

    @Composable
    override fun error(e: Exception) {

    }

    @Composable
    override fun data(viewState: MapsViewState) {
        MapsViewData(controller, viewState)
    }

}

@Composable
private fun MapsViewData(controller: MapsController, viewState: MapsViewState) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text("MapsView")
        Button(onClick = controller::buttonClick) {
            Text(viewState.txt)
        }
    }
}


@Composable
private fun MainMenuLoading() {
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