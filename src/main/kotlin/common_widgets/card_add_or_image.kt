package common_widgets

import androidx.compose.runtime.Composable
import data.types.ContentSourceType

@Composable
fun CardAddOrImage(
    label: String,
    image: ContentSourceType,
    onClick: () -> Unit,
) {
    when (image) {
        is ContentSourceType.File -> {
            CardImage(
                label = label,
                pathToFile = image,
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
                text = "Err: ${image.value}",
            )
        }
    }
}