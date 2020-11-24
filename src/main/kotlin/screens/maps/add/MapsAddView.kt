package screens.maps.add

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import common_widgets.*
import org.koin.core.inject
import screens.BaseView
import ui.greyAccent
import ui.orangeAccent
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
    override fun setContent() {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize().align(Alignment.TopStart),
                verticalArrangement = spacedBy20dp
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = spacedBy20dp
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
                    horizontalArrangement = spacedBy20dp
                ) {
                    CardAdd(
                        label = "add logo",
                        onClick = ::addLogoClick
                    )
                    CardAdd(
                        label = "add map",
                        onClick = ::addMapClick
                    )
                    CardAdd(
                        label = "add wallpaper",
                        onClick = ::addWallpaperClick
                    )
                }
                SwitchLable(
                    modifier = Modifier.fillMaxWidth(),
                    label = "Competitive",
                    isChecked = controller.getViewState().isCompetitive,
                    onCheckedChange = ::competitiveChecked
                )
            }
            Row(
                modifier = Modifier.align(Alignment.BottomEnd),
                horizontalArrangement = spacedBy20dp
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