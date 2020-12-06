package common_widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ui.dark
import ui.fontSize14sp
import ui.roundedCorner5dp
import ui.verdanaBold

@Composable
fun ButtonApp(
    label: String,
    color: Color,
    modifier: Modifier = Modifier,
    isActive: Boolean = true,
    onClick: () -> Unit
) {
    if (isActive) {
        Container(
            modifier = Modifier.size(100.dp, 40.dp).then(modifier).clickable(onClick = onClick),
            color = color,
            shape = roundedCorner5dp
        ) {
            Text(
                text = label.toUpperCase(),
                fontFamily = verdanaBold,
                fontSize = fontSize14sp,
                color = dark,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    } else {
        Container(
            modifier = Modifier.size(100.dp, 40.dp).then(modifier),
            color = color.copy(alpha = 0.2f),
            shape = roundedCorner5dp
        ) {
            Text(
                text = label.toUpperCase(),
                fontFamily = verdanaBold,
                fontSize = fontSize14sp,
                color = dark,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}