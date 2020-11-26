package common_widgets

import androidx.compose.runtime.Composable

@Composable
fun CardAddOrImage(
    label: String,
    pathToImage: String,
    onClick: () -> Unit,
) {
    if (pathToImage.isBlank()) {
        CardAdd(
            label = label,
            onClick = onClick
        )
    } else {
        CardImage(
            pathToFile = pathToImage,
            onClick = onClick,
        )
    }
}