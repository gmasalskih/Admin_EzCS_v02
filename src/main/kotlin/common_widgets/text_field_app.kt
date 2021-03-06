package common_widgets

import androidx.compose.foundation.layout.preferredWidth
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import ui.*

@Composable
fun TextFieldApp(value: String, label: String, modifier: Modifier = Modifier, onTextChanged: (String) -> Unit = {}) {
    TextField(
        value = value,
        onValueChange = onTextChanged,
        textStyle = TextStyle(
            color = greyAccent,
            fontSize = fontSize14sp,
            fontFamily = verdanaRegular,
        ),
        label = {
            Text(
                text = label,
                modifier = Modifier.then(modifier),
                color = greyAccent.copy(alpha = 0.4f),
                fontFamily = verdanaRegular,
                fontSize = fontSize12sp
            )
        },
        modifier = Modifier.preferredWidth(220.dp).then(modifier),
        shape = roundedCorner5dp,
    )
}