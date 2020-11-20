package common_widgets

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.input.pointer.pointerMoveFilter

fun Modifier.onHover(hasHover: (Boolean) -> Unit): Modifier = composed {
    var isHover by remember { mutableStateOf(false) }
    hasHover.invoke(isHover)
    pointerMoveFilter(
        onEnter = { isHover = true; true },
        onExit = { isHover = false; true }
    )
}