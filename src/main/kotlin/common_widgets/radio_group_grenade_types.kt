package common_widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import data.enums.GrenadeTypes
import ui.fontSize20sp
import ui.greyAccent
import ui.spacedBy10dp
import ui.verdanaRegular

@Composable
fun RadioGroupGrenadeTypes(
    modifier: Modifier = Modifier,
    onTypeSelected: (GrenadeTypes) -> Unit,
    grenadeTypeSelected: GrenadeTypes = GrenadeTypes.SMOKE
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
                isSelected = grenadeTypeSelected === GrenadeTypes.SMOKE ,
                onClick = {
                    onTypeSelected(GrenadeTypes.SMOKE)
                }
            )
            RadioButtonLabel(
                label = "Molotov",
                isSelected = grenadeTypeSelected === GrenadeTypes.MOLOTOV,
                onClick = {
                    onTypeSelected(GrenadeTypes.MOLOTOV)
                }
            )
            RadioButtonLabel(
                label = "Flash",
                isSelected = grenadeTypeSelected === GrenadeTypes.FLASH,
                onClick = {
                    onTypeSelected(GrenadeTypes.FLASH)
                }
            )
        }
    }
}

/*




 */