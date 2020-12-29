package common_widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ui.*

@Composable
fun TopBarApp(title: String, hasBackArrowButton: Boolean = false, clickBack: () -> Unit = {}) {
    Container(
        modifier = Modifier.fillMaxWidth().preferredHeight(appBarSize),
        color = dark
    ) {
        if (hasBackArrowButton) {
            Box(
                modifier = Modifier.fillMaxHeight().aspectRatio(1f).clickable(onClick = clickBack),
                contentAlignment = Alignment.Center
            ) {
                IconApp(
                    pathToIcon = "icons/icon_back_arrow.png",
                    tint = greyAccent
                )
            }
        }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = title,
                fontFamily = verdanaBold,
                fontSize = fontSize20sp,
                color = greyAccent
            )
        }
    }
}