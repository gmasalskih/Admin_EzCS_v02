package common_widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ui.fontSize20sp
import ui.greyAccent
import ui.spacedBy10dp
import ui.verdanaRegular

@Composable
inline fun <reified T : Enum<T>> RadioGroupFilter(
    modifier: Modifier = Modifier,
    label: String,
    crossinline onFilterSelected: (T) -> Unit,
    filterSelected: T,
) {
    Column(
        modifier = Modifier.then(modifier),
        verticalArrangement = spacedBy10dp
    ) {
        Text(
            text = label,
            fontFamily = verdanaRegular,
            color = greyAccent,
            fontSize = fontSize20sp
        )
        enumValues<T>().forEach { enumItem ->
            RadioButtonLabel(
                label = enumItem.name,
                isSelected = enumItem === filterSelected,
                onClick = {
                    onFilterSelected(enumItem)
                }
            )
        }
    }
}