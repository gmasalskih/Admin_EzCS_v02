package common_widgets

import androidx.compose.material.Icon
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.imageResource

@Composable
fun AppIcon(pathToIcon: String, tint: Color) {
    Icon(
        asset = imageResource(pathToIcon),
        tint = tint,
        modifier = Modifier.preferredHeight(iconHeight)
    )
}