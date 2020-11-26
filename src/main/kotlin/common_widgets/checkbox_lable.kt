package common_widgets

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxConstants
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ui.greyAccent
import ui.orangeAccent

@Composable
fun CheckboxLable(
    label: String,
    checked: Boolean,
    modifier: Modifier = Modifier,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.then(modifier),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = spacedBy5dp
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = CheckboxConstants.defaultColors(
                checkedColor = orangeAccent,
                uncheckedColor = greyAccent
            )
        )
        Label(
            label = label,
            checked = checked
        )
    }
}