package common_widgets

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.TextUnit
import ui.greyAccent
import ui.orangeAccent
import ui.verdanaRegular

@Composable
fun Label(
    label: String,
    checked: Boolean,
    checkedColor: Color = orangeAccent,
    uncheckedColor: Color = greyAccent,
    fontFamily: FontFamily = verdanaRegular,
    fontSize: TextUnit = fontSize20sp
) {
    Text(
        text = label,
        fontFamily = fontFamily,
        color = if (checked) checkedColor else uncheckedColor,
        fontSize = fontSize
    )
}

/*




 */