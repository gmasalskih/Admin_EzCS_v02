package common_widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ui.dark
import ui.verdanaBold

@Composable
fun Btn(label: String, color: Color, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Surface(
        modifier = Modifier.size(100.dp, 40.dp).then(modifier).clickable(onClick = onClick),
        color = color,
        shape = RoundedCornerShape(5.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = label.toUpperCase(),
                fontFamily = verdanaBold,
                fontSize = 14.sp,
                color = dark,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}