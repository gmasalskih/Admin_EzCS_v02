package screens.maps.add

import androidx.compose.foundation.layout.*
import androidx.compose.material.Switch
import androidx.compose.material.SwitchConstants
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import common_widgets.CardAdd
import common_widgets.ButtonApp
import common_widgets.TextFieldApp
import org.koin.core.inject
import screens.BaseView
import screens.maps.add.MapsAddController
import ui.greyAccent
import ui.orangeAccent
import ui.verdanaRegular
import utils.fileChooser
import utils.toValidId
import utils.toValidName

class MapsAddView : BaseView<MapsAddController>() {
    override val controller by inject<MapsAddController>()

    private fun mapIdChange(mapId: String) {
        controller.setViewState(
            controller.getViewState().copy(
                mapId = mapId.toValidId()
            )
        )
    }

    private fun mapNameChange(mapName: String) {
        controller.setViewState(
            controller.getViewState().copy(
                mapName = mapName.toValidName()
            )
        )
    }

    private fun addLogoClick() {
        fileChooser("Logo")
    }

    private fun addMapClick() {
        //TODO("Not yet implemented")
    }

    private fun addWallpaperClick() {
        //TODO("Not yet implemented")
    }

    private fun competitiveChecked(value: Boolean) {
        controller.setViewState(
            controller.getViewState().copy(
                isCompetitive = value
            )
        )
    }

    private fun clearBtnClick() {
        controller.clearViewState()
    }

    private fun submitBtnClick() {
        //TODO("Not yet implemented")
    }

    @Composable
    override fun render() = renderContent {
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
                    TextFieldApp(
                        value = controller.getViewState().mapId,
                        label = "Enter map ID",
                        onTextChanged = ::mapIdChange
                    )
                    //Map name
                    TextFieldApp(
                        value = controller.getViewState().mapName,
                        label = "Enter map name",
                        onTextChanged = ::mapNameChange
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    CardAdd(
                        title = "add logo",
                        click = ::addLogoClick
                    )
                    CardAdd(
                        title = "add map",
                        click = ::addMapClick
                    )
                    CardAdd(
                        title = "add wallpaper",
                        click = ::addWallpaperClick
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Switch(
                        checked = controller.getViewState().isCompetitive,
                        onCheckedChange = ::competitiveChecked,
                        colors = SwitchConstants.defaultColors(
                            checkedThumbColor = orangeAccent,
                            uncheckedThumbColor = greyAccent
                        )
                    )
                    Text(
                        text = "Competitive",
                        fontFamily = verdanaRegular,
                        fontSize = 20.sp,
                        color = if (controller.getViewState().isCompetitive) orangeAccent else greyAccent
                    )
                }
            }
            Row(
                modifier = Modifier.align(Alignment.BottomEnd),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                ButtonApp(
                    label = "clear",
                    color = greyAccent,
                    onClick = ::clearBtnClick
                )
                ButtonApp(
                    label = "submit",
                    color = orangeAccent,
                    onClick = ::submitBtnClick
                )
            }
        }
    }
}