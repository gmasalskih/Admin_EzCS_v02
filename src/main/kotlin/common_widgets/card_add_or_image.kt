package common_widgets

import androidx.compose.runtime.Composable

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