package common_widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
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
            viewComponent.controller.viewState.title.toUpperCase(),
            viewComponent.controller.isNavigableBack(),
            viewComponent.controller::back
        )
        //Content
        Box(modifier = Modifier.fillMaxSize()) {
            content()
            when (val typeScreenState = viewComponent.controller.viewState.stateType) {
                is StateType.Loading -> {
                    Container(
                        modifier = Modifier.fillMaxSize(),
                        color = Color.Black.copy(alpha = 0.4f)
                    ) {
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
                }
                is StateType.Error -> {
                    Dialog(
                        onDismissRequest = viewComponent.controller::showData
                    ) {
                        Container(
                            modifier = Modifier.fillMaxSize(),
                            color = dark
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize().padding(10.dp)
                            ) {
                                Text(
                                    modifier = Modifier.align(alignment = Alignment.Center),
                                    text = typeScreenState.err.message ?: "Unknown error...",
                                    color = greyAccent
                                )
                                Container(
                                    modifier = Modifier.size(100.dp, 40.dp)
                                        .align(alignment = Alignment.BottomCenter)
                                        .clickable { viewComponent.controller.showData() },
                                    color = greyAccent,
                                    shape = roundedCorner5dp
                                ) {
                                    Text(
                                        text = "OK",
                                        fontSize = fontSize14sp,
                                        color = dark,
                                        modifier = Modifier.align(Alignment.Center)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}