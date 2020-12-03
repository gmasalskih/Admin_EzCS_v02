package common_widgets

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import screens.BaseView
import data.types.StateType
import ui.*

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
        Box(modifier = Modifier.fillMaxSize()) {
            content()
            when (val typeScreenState = viewComponent.controller.getViewState().stateType) {
                is StateType.Loading -> {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = Color.Black.copy(alpha = 0.4f),
                        content = {}
                    )
                    Row(
                        modifier = Modifier.align(alignment = Alignment.Center),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = spacedBy20dp
                    ) {
                        Text(
                            text = "Loading...",
                            fontFamily = verdanaBold,
                            fontSize = fontSize20sp,
                            color = greyAccent
                        )
                        CircularProgressIndicator(color = orangeAccent)
                    }

                }
                is StateType.Error -> {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = Color.Black.copy(alpha = 0.4f),
                        content = {}
                    )
                    Text(
                        text = typeScreenState.err.message ?: "Unknown error...",
                        fontFamily = verdanaBold,
                        fontSize = fontSize20sp,
                        color = orangeAccent
                    )

                }
                else -> {
                }
            }
        }
    }
}