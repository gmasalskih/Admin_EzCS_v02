package screens.map_holder.edit

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import common_widgets.*
import org.koin.core.inject
import screens.BaseView
import ui.orangeAccent
import utils.fileChooser
import utils.toValidName

class MapHolderEditView(val id: String) : BaseView<MapHolderEditController>() {
    override val controller by inject<MapHolderEditController>()

    init {
        setId(id)
    }

    private fun setId(id: String) {
        controller.setViewState(
            controller.getViewState().copy(
                id = id
            )
        )
    }

    private fun onCompetitiveChange(value: Boolean) {
        controller.setViewState(
            controller.getViewState().copy(
                isCompetitive = value
            )
        )
    }

    private fun onSubmit() {
    }

    private fun onWallpaperChange() {
        val newPathToWallpaper = fileChooser("Select logo", "png") ?: return
        if (!controller.getViewState().pathToWallpaper.contains(newPathToWallpaper)) {
            controller.setViewState(
                controller.getViewState().copy(
                    pathToWallpaper = newPathToWallpaper
                )
            )
        }
    }

    private fun onMapChange() {
        val newPathToMap = fileChooser("Select logo", "png") ?: return
        if (!controller.getViewState().pathToMap.contains(newPathToMap)) {
            controller.setViewState(
                controller.getViewState().copy(
                    pathToMap = newPathToMap
                )
            )
        }
    }

    private fun onLogoChange() {
        val newPathToLogo = fileChooser("Select logo", "png") ?: return
        if (!controller.getViewState().pathToLogo.contains(newPathToLogo)) {
            controller.setViewState(
                controller.getViewState().copy(
                    pathToLogo = newPathToLogo
                )
            )
        }
    }

    private fun onNameChange(name: String) {
        controller.setViewState(
            controller.getViewState().copy(
                name = name.toValidName()
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
                        value = controller.getViewState().id,
                        label = "Map ID",
                        onTextChanged = {}
                    )
                    //Map name
                    TextFieldApp(
                        value = controller.getViewState().name,
                        label = "Map name",
                        onTextChanged = ::onNameChange
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = spacedBy20dp
                ) {

                    CardAddOrImage(
                        label = "add logo",
                        pathToImage = controller.getViewState().pathToLogo,
                        onClick = ::onLogoChange
                    )
                    CardAddOrImage(
                        label = "add map",
                        pathToImage = controller.getViewState().pathToMap,
                        onClick = ::onMapChange
                    )
                    CardAddOrImage(
                        label = "add wallpaper",
                        pathToImage = controller.getViewState().pathToWallpaper,
                        onClick = ::onWallpaperChange
                    )
                }
                SwitchLable(
                    label = "Competitive",
                    isChecked = controller.getViewState().isCompetitive,
                    modifier = Modifier.fillMaxWidth(),
                    onCheckedChange = ::onCompetitiveChange
                )
            }
            ButtonApp(
                label = "submit",
                color = orangeAccent,
                onClick = ::onSubmit,
                modifier = Modifier.align(Alignment.BottomEnd)
            )
        }
    }

}