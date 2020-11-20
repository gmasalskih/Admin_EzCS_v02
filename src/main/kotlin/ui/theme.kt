package ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val Colors = lightColors(
    primary = dark,
    primaryVariant = light,
    secondary = Color.Red
)

@Composable
fun AdminEzCSTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = Colors,
        content = content,
    )
}