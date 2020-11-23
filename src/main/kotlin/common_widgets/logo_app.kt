package common_widgets

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import ui.orangeAccent
import ui.verdanaBold

@Composable
fun LogoApp() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Admin EzCS",
            fontFamily = verdanaBold,
            fontSize = TextUnit.Sp(20),
            color = orangeAccent,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 20.dp)
        )
        Divider(
            color = orangeAccent,
            thickness = 2.dp,
            modifier = Modifier.preferredWidth(140.dp)
        )
        Box(
            modifier = Modifier.fillMaxWidth().preferredHeight(20.dp),
        )

    }
}