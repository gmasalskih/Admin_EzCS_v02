package common_widgets

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.onActive
import androidx.compose.runtime.onDispose
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import utils.ViewComponent


@Composable
fun <VC : ViewComponent> Screen(viewComponent: VC, content: @Composable () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        //AppBar
        TopBarApp(
            viewComponent.controller.getViewState().title,
            viewComponent.controller.isNavigableBack(),
            viewComponent.controller::back
        )
        //Content
        Box(
            modifier = Modifier.fillMaxSize().padding(20.dp),
        ) {
            content()
        }
    }
}