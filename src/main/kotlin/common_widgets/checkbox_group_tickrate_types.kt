package common_widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import data.types.TickrateType
import ui.fontSize20sp
import ui.greyAccent
import ui.spacedBy10dp
import ui.verdanaRegular

@Composable
fun CheckboxGroupTickrateTypes(
    modifier: Modifier = Modifier,
    listTickrateTypes: List<TickrateType>,
    onTickrateTypeClick: (TickrateType) -> Unit
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
                checked = listTickrateTypes.contains(TickrateType.TICKRATE_64),
                onCheckedChange = { onTickrateTypeClick(TickrateType.TICKRATE_64) }
            )
            CheckboxLable(
                label = "128",
                checked = listTickrateTypes.contains(TickrateType.TICKRATE_128),
                onCheckedChange = { onTickrateTypeClick(TickrateType.TICKRATE_128) }
            )
        }
    }
}