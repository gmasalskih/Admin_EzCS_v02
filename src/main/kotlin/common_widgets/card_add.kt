package common_widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ui.greyAccent
import ui.light
import ui.verdanaBold

@Composable
fun CardAdd(label: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier.size(100.dp, 150.dp).clickable(onClick = onClick),
        backgroundColor = greyAccent,
        shape = roundedCorner5dp,
        elevation = elevation6dp
    ) {
        Box(
            modifier = Modifier.fillMaxSize().padding(10.dp)
        ) {
            IconApp(
                pathToIcon = "icons/icon_add.png",
                tint = light,
                modifier = Modifier.align(Alignment.Center)
            )
            Text(
                text = label.toUpperCase(),
                fontFamily = verdanaBold,
                fontSize = fontSize6sp,
                color = light,
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }
}