package common_widgets

import androidx.compose.runtime.Composable
import data.types.ContentSourceType

@Composable
fun CardAddOrImage(
    label: String,
    pathToFile: ContentSourceType,
    onClick: () -> Unit,
) {
    when (pathToFile) {
        is ContentSourceType.File -> {
            CardImage(
                label = label,
                pathToFile = pathToFile,
                onClick = onClick,
            )
        }
        is ContentSourceType.Empty -> {
            CardAdd(
                label = label,
                onClick = onClick
            )
        }
        else -> {
            TextApp(
                text = "Err: ${pathToFile.value}",
            )
        }
    }
}