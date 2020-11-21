package common_widgets

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import utils.ViewComponent


@Composable
fun <VC : ViewComponent> Screen(viewComponent: VC, content: @Composable () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        //AppBar
        AppBar(
            viewComponent.controller.viewState.title,
            viewComponent.controller.router.isNavigableBack(),
            viewComponent.controller.router
        )
        //Content
        Box(
            modifier = Modifier.fillMaxSize().padding(20.dp),
        ) {
            content.invoke()
        }
    }
}