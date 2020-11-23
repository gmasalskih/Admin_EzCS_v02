package screens.maps.edit

import androidx.compose.foundation.layout.*
import androidx.compose.material.Switch
import androidx.compose.material.SwitchConstants
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import common_widgets.ButtonApp
import common_widgets.CardImage
import common_widgets.TextFieldApp
import org.koin.core.inject
import screens.BaseView
import ui.greyAccent
import ui.orangeAccent
import ui.verdanaRegular
import utils.toValidName

class MapsEditView(val id: String) : BaseView<MapsEditController>() {
    override val controller by inject<MapsEditController>()

    private fun competitiveChecked(value: Boolean) {
        controller.setViewState(
            controller.getViewState().copy(
                isCompetitive = value
            )
        )
    }

    private fun submitBtnClick() {
        controller.setViewState(
            controller.getViewState().copy(
                pathToLogo = "https://golaya-pizda.com/wp-content/uploads/2017/06/chastnoe-foto-403.jpg"
            )
        )
    }

    private fun addWallpaperClick() {
        //TODO("Not yet implemented")
    }

    private fun addMapClick() {
        //TODO("Not yet implemented")
    }

    private fun onClickLogo() {
        //TODO("Not yet implemented")
    }

    private fun onMapNameChange(mapName: String) {
        controller.setViewState(
            controller.getViewState().copy(
                mapName = mapName.toValidName()
            )
        )
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
                        label = "Map ID",
                        onTextChanged = {}
                    )
                    //Map name
                    TextFieldApp(
                        value = controller.getViewState().mapName,
                        label = "Map name",
                        onTextChanged = ::onMapNameChange
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    //logo
                    CardImage(
                        pathToImage = controller.getViewState().pathToLogo,
                        fileName = controller.getViewState().pathToLogo.split("/").last(),
                        onClick = ::onClickLogo
                    )
                    //map
                    CardImage(
                        pathToImage = controller.getViewState().pathToMapImg,
                        fileName = controller.getViewState().pathToMapImg.split("/").last(),
                        onClick = ::onClickLogo
                    )
                    //wallpaper
                    CardImage(
                        pathToImage = controller.getViewState().pathToWallpaper,
                        fileName = controller.getViewState().pathToWallpaper.split("/").last(),
                        onClick = ::onClickLogo
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
            ButtonApp(
                label = "submit",
                color = orangeAccent,
                onClick = ::submitBtnClick,
                modifier = Modifier.align(Alignment.BottomEnd)
            )
        }
    }
}