package common_widgets

import androidx.compose.runtime.Composable
import data.types.ContentSourceType

@Composable
fun CardAddOrImage(
    label: String,
    image: String,
    onClick: () -> Unit,
) {
    if (image.isBlank()) {
        CardAdd(
            label = label,
            onClick = onClick
        )
    } else {
        CardImage(
            pathToFile = image,
            onClick = onClick,
        )
    }
}

@Composable
fun CardAddOrImage(
    label: String,
    image: ContentSourceType,
    onClick: () -> Unit,
) {
    when (image) {
        is ContentSourceType.File -> {
            CardImage(
                pathToFile = image.value,
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