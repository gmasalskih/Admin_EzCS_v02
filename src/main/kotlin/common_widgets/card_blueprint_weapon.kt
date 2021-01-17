package common_widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ui.*

@Composable
fun CardBlueprintWeapon(
    modifier: Modifier = Modifier,
    name: String,
    isSelected: Boolean = false,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.size(100.dp, 150.dp).then(modifier).clickable(onClick = onClick),
        shape = roundedCorner5dp,
        elevation = elevation6dp
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = if (isSelected) orangeAccent else greyAccent
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = name.toUpperCase(),
                    fontFamily = verdanaBold,
                    fontSize = fontSize12sp,
                    modifier = Modifier.align(Alignment.Center),
                    color = dark
                )
            }
        }
    }
}