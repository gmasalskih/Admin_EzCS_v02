package common_widgets

import androidx.compose.foundation.layout.Row
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonConstants
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ui.greyAccent
import ui.orangeAccent
import ui.verdanaRegular

@Composable
fun RadioButtonLabel(
    label: String = "",
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier.then(modifier),
        horizontalArrangement = spacedBy5dp,
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = isSelected,
            colors = RadioButtonConstants.defaultColors(
                selectedColor = orangeAccent,
                unselectedColor = greyAccent
            ),
            onClick = onClick
        )
        Label(
            label = label,
            checked = isSelected
        )
    }
}