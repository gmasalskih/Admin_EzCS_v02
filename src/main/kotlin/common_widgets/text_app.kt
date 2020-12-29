package common_widgets

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import ui.fontSize14sp
import ui.greyAccent
import ui.verdanaRegular
import utils.toValidId

@Composable
fun TextApp(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign? = null,
    fontFamily: FontFamily = verdanaRegular,
    fontSize: TextUnit = fontSize14sp,
    color: Color = greyAccent
) {
    Text(
        modifier = Modifier.then(modifier),
        text = text,
        textAlign = textAlign,
        fontFamily = fontFamily,
        fontSize = fontSize,
        color = color
    )
}