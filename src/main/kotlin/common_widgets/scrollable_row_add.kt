package common_widgets

import androidx.compose.foundation.ScrollableRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun <T> ScrollableRowAdd(
    modifier: Modifier = Modifier,
    cardAdd: @Composable () -> Unit,
    cardItem: @Composable (T) -> Unit,
    items: Iterable<T>
) {
    Row(
        modifier = modifier,
        horizontalArrangement = spacedBy20dp
    ) {
        cardAdd()
        ScrollableRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = spacedBy20dp,
            children = {
                items.forEach { item -> cardItem(item) }
            }
        )
    }
}