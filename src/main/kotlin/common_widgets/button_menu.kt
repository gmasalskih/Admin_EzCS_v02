package common_widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ui.dark
import ui.fontSize12sp
import ui.greyAccent
import ui.verdanaBold

@Composable
fun ButtonMenu(
    isActive: Boolean = false,
    logoPath: String,
    title: String,
    click: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxWidth().preferredHeight(80.dp).clickable(onClick = click),
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = if (isActive) greyAccent else dark
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconApp(
                    pathToIcon = logoPath,
                    tint = if (isActive) dark else greyAccent
                )
                Text(
                    text = title.toUpperCase(),
                    color = if (isActive) dark else greyAccent,
                    fontFamily = verdanaBold,
                    fontSize = fontSize12sp
                )
            }
        }
    }
}