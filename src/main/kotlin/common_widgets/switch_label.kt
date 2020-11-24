package common_widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Switch
import androidx.compose.material.SwitchConstants
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ui.greyAccent
import ui.orangeAccent
import ui.verdanaRegular

@Composable
fun SwitchLable(
    label: String = "",
    isChecked: Boolean = false,
    checkedThumbColor: Color = orangeAccent,
    uncheckedThumbColor: Color = greyAccent,
    modifier: Modifier = Modifier,
    onCheckedChange: (Boolean) -> Unit,
) {
    Row(
        modifier = Modifier.then(modifier),
        horizontalArrangement = spacedBy20dp
    ) {
        Switch(
            checked = isChecked,
            onCheckedChange = onCheckedChange,
            colors = SwitchConstants.defaultColors(
                checkedThumbColor = checkedThumbColor,
                uncheckedThumbColor = uncheckedThumbColor
            )
        )
        Text(
            text = label,
            fontFamily = verdanaRegular,
            fontSize = fontSize20sp,
            color = if (isChecked) checkedThumbColor else uncheckedThumbColor
        )
    }
}