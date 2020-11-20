package common_widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import router.Router
import ui.dark
import ui.greyAccent
import ui.verdanaBold

@Composable
fun AppBar(title: String, hasBackArrowButton: Boolean = false, router: Router) {
    Surface(
        modifier = Modifier.fillMaxWidth().preferredHeight(appBarSize),
        color = dark
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            if (hasBackArrowButton){
                Box(
                    modifier = Modifier.fillMaxHeight().aspectRatio(1f).clickable { router.back() },
                    alignment = Alignment.Center
                ) {
                    AppIcon(
                        pathToIcon = "icons/back_arrow.png",
                        tint = greyAccent
                    )
                }
            }
            Box(
                modifier = Modifier.fillMaxSize(),
                alignment = Alignment.Center
            ){
                Text(
                    text = title,
                    fontFamily = verdanaBold,
                    fontSize = heading,
                    color = greyAccent
                )
            }
        }
    }
}