package common_widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ui.greyAccent
import ui.light
import ui.verdanaBold

@Composable
fun AddCard(title: String, click: () -> Unit) {
    Card(
        modifier = Modifier.size(100.dp, 150.dp).clickable(onClick = click),
        backgroundColor = greyAccent,
        shape = RoundedCornerShape(5.dp),
        elevation = 6.dp
    ) {
        Box(
            modifier = Modifier.fillMaxSize().padding(10.dp)
        ) {
            AppIcon(
                pathToIcon = "icons/icon_add.png",
                tint = light,
                modifier = Modifier.align(Alignment.Center)
            )
            Text(
                text = title.toUpperCase(),
                fontFamily = verdanaBold,
                fontSize = 6.sp,
                color = light,
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }
}