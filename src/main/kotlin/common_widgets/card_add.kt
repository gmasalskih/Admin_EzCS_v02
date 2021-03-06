package common_widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ui.*

@Composable
fun CardAdd(label: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier.size(100.dp, 150.dp).clickable(onClick = onClick),
        backgroundColor = greyAccent,
        shape = roundedCorner5dp,
        elevation = elevation6dp
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            IconApp(
                pathToIcon = Icons.Add,
                tint = light,
                modifier = Modifier.align(Alignment.Center)
            )
            Text(
                text = label.toUpperCase(),
                textAlign = TextAlign.Center,
                fontFamily = verdanaBold,
                fontSize = fontSize8sp,
                color = light,
                modifier = Modifier.align(Alignment.BottomCenter).offset(y = (-10).dp)
            )
        }
    }
}