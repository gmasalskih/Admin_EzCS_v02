package screens.maps_add

import androidx.compose.foundation.layout.*
import androidx.compose.material.Switch
import androidx.compose.material.SwitchConstants
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import common_widgets.AddCard
import common_widgets.Btn
import common_widgets.DecoratedTextField
import ui.greyAccent
import ui.orangeAccent
import ui.verdanaRegular

@Composable
internal fun MapAddContent(view: MapsAddView) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize().align(Alignment.TopStart),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                //Map ID
                DecoratedTextField(
                    value = view.controller.viewState.mapId,
                    label = "Enter map ID",
                    onTextChanged = view::mapIdChange
                )
                //Map name
                DecoratedTextField(
                    value = view.controller.viewState.mapName,
                    label = "Enter map name",
                    onTextChanged = view::mapNameChange
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                AddCard(
                    title = "add logo",
                    click = view::addLogoClick
                )
                AddCard(
                    title = "add map",
                    click = view::addMapClick
                )
                AddCard(
                    title = "add wallpaper",
                    click = view::addWallpaperClick
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Switch(
                    checked = view.controller.viewState.isCompetitive,
                    onCheckedChange = view::competitiveChecked,
                    colors = SwitchConstants.defaultColors(
                        checkedThumbColor = orangeAccent,
                        uncheckedThumbColor = greyAccent
                    )
                )
                Text(
                    text = "Competitive",
                    fontFamily = verdanaRegular,
                    fontSize = 20.sp,
                    color = if (view.controller.viewState.isCompetitive) orangeAccent else greyAccent
                )
            }
        }
        Row(
            modifier = Modifier.align(Alignment.BottomEnd),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Btn(
                label = "clear",
                color = greyAccent,
                onClick = view::clearBtnClick
            )
            Btn(
                label = "submit",
                color = orangeAccent,
                onClick = view::submitBtnClick
            )
        }
    }
}