package common_widgets

import androidx.compose.material.Icon
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.imageResource
import ui.iconHeight

@Composable
fun IconApp(pathToIcon: String, tint: Color, modifier: Modifier = Modifier) {
    Icon(
        bitmap = imageResource(pathToIcon),
        tint = tint,
        modifier = Modifier.preferredHeight(iconHeight).then(modifier)
    )
}