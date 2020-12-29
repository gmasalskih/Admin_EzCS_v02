package common_widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import data.types.GrenadeType
import data.types.TickrateFilterType
import ui.fontSize20sp
import ui.greyAccent
import ui.spacedBy10dp
import ui.verdanaRegular

@Composable
fun RadioGroupGrenadeTypes(
    modifier: Modifier = Modifier,
    onTypeSelected: (GrenadeType) -> Unit,
    grenadeTypeSelected: GrenadeType = GrenadeType.SMOKE,
) {
    Column(
        modifier = Modifier.then(modifier),
        verticalArrangement = spacedBy10dp
    ) {
        Text(
            text = "Grenade type:",
            fontFamily = verdanaRegular,
            color = greyAccent,
            fontSize = fontSize20sp
        )
        Row(
            horizontalArrangement = spacedBy10dp
        ) {
            RadioButtonLabel(
                label = "Smoke",
                isSelected = grenadeTypeSelected === GrenadeType.SMOKE ,
                onClick = {
                    onTypeSelected(GrenadeType.SMOKE)
                }
            )
            RadioButtonLabel(
                label = "Molotov",
                isSelected = grenadeTypeSelected === GrenadeType.MOLOTOV,
                onClick = {
                    onTypeSelected(GrenadeType.MOLOTOV)
                }
            )
            RadioButtonLabel(
                label = "Flash",
                isSelected = grenadeTypeSelected === GrenadeType.FLASH,
                onClick = {
                    onTypeSelected(GrenadeType.FLASH)
                }
            )
        }
    }
}