package common_widgets

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.TextUnit
import ui.fontSize14sp
import ui.greyAccent
import ui.verdanaRegular
import utils.toValidId

@Composable
fun TextApp(
    text: String,
    fontFamily: FontFamily = verdanaRegular,
    fontSize: TextUnit = fontSize14sp,
    color: Color = greyAccent
) {
    Text(
        text = text,
        fontFamily = fontFamily,
        fontSize = fontSize,
        color = color
    )
}