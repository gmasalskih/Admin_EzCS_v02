package screens.maps.edit

import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import common_widgets.*
import org.koin.core.inject
import screens.BaseView
import ui.orangeAccent
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

    override fun onViewCreate() {
        super.onViewCreate()
        setContent {
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
                        horizontalArrangement = spacedBy20dp
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
                    SwitchLable(
                        label = "Competitive",
                        isChecked = controller.getViewState().isCompetitive,
                        modifier = Modifier.fillMaxWidth(),
                        onCheckedChange = ::competitiveChecked
                    )
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
}