package common_widgets

import androidx.compose.foundation.ScrollableRow
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.InternalLayoutApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ui.spacedBy20dp

@Composable
fun <T> ScrollableItemRow(
    modifier: Modifier = Modifier,
    items: Iterable<T>,
    cardItem: @Composable (T) -> Unit,
) {
    ScrollableRow(
        modifier = Modifier.then(modifier),
        horizontalArrangement = spacedBy20dp,
        content = {
            items.forEach { item ->
                cardItem(item)
            }
        }
    )
}