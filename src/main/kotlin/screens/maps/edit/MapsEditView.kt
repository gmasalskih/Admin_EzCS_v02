package screens.maps.edit

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
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

    override fun onViewCreate() {
        super.onViewCreate()
        controller.onViewCreate()
    }

    override fun onViewDestroy() {
        super.onViewDestroy()
        controller.getViewState()
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
                        pathToFile = controller.getViewState().pathToLogo,
                        onClick = ::onClickLogo
                    )
                    //map
                    CardImage(
                        pathToFile = controller.getViewState().pathToMapImg,
                        onClick = ::onClickLogo
                    )
                    //wallpaper
                    CardImage(
                        pathToFile = controller.getViewState().pathToWallpaper,
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