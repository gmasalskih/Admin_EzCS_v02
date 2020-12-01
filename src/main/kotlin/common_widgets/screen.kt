package common_widgets

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import screens.BaseView

@Composable
inline fun Screen(viewComponent: BaseView<*>, crossinline content: @Composable () -> Unit) {
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