package common_widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import data.enums.TickRate
import ui.fontSize20sp
import ui.greyAccent
import ui.spacedBy10dp
import ui.verdanaRegular

@Composable
fun CheckboxGroupTickrateTypes(
    modifier: Modifier = Modifier,
    listTickrateTypes: List<TickRate>,
    onTickrateTypeClick: (TickRate) -> Unit
) {
    Column(
        modifier = Modifier.then(modifier),
        verticalArrangement = spacedBy10dp
    ) {
        Text(
            text = "Tickrate:",
            fontFamily = verdanaRegular,
            color = greyAccent,
            fontSize = fontSize20sp
        )
        Row(
            horizontalArrangement = spacedBy10dp
        ) {
            CheckboxLable(
                label = "64",
                checked = listTickrateTypes.contains(TickRate.TICKRATE_64),
                onCheckedChange = { onTickrateTypeClick(TickRate.TICKRATE_64) }
            )
            CheckboxLable(
                label = "128",
                checked = listTickrateTypes.contains(TickRate.TICKRATE_128),
                onCheckedChange = { onTickrateTypeClick(TickRate.TICKRATE_128) }
            )
        }
    }
}