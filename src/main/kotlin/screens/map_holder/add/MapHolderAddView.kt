package screens.map_holder.add

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

class MapHolderAddView : BaseView<MapHolderController>() {
    override val controller by inject<MapHolderController>()

    private fun onIdChange(id: String) {
        controller.setViewState(
            controller.getViewState().copy(
                id = id.toValidId()
            )
        )
    }

    private fun onNameChange(name: String) {
        controller.setViewState(
            controller.getViewState().copy(
                name = name.toValidName()
            )
        )
    }

    private fun onLogoAdd() {
        val newPathToLogo = fileChooser("Select logo", "png") ?: return
        if (!controller.getViewState().pathToLogo.contains(newPathToLogo)) {
            controller.setViewState(
                controller.getViewState().copy(
                    pathToLogo = newPathToLogo
                )
            )
        }
    }

    private fun onMapAdd() {
        val newPathToMap = fileChooser("Select logo", "png") ?: return
        if (!controller.getViewState().pathToMap.contains(newPathToMap)) {
            controller.setViewState(
                controller.getViewState().copy(
                    pathToMap = newPathToMap
                )
            )
        }
    }

    private fun onWallpaperAdd() {
        val newPathToWallpaper = fileChooser("Select logo", "png") ?: return
        if (!controller.getViewState().pathToWallpaper.contains(newPathToWallpaper)) {
            controller.setViewState(
                controller.getViewState().copy(
                    pathToWallpaper = newPathToWallpaper
                )
            )
        }
    }

    private fun onCompetitiveChange(value: Boolean) {
        controller.setViewState(
            controller.getViewState().copy(
                isCompetitive = value
            )
        )
    }

    private fun onClear() {
        controller.clearState()
    }

    private fun onSubmit() {
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
                        value = controller.getViewState().id,
                        label = "Enter map ID",
                        onTextChanged = ::onIdChange
                    )
                    //Map name
                    TextFieldApp(
                        value = controller.getViewState().name,
                        label = "Enter map name",
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
                        onClick = ::onLogoAdd
                    )
                    CardAddOrImage(
                        label = "add map",
                        pathToImage = controller.getViewState().pathToMap,
                        onClick = ::onMapAdd
                    )
                    CardAddOrImage(
                        label = "add wallpaper",
                        pathToImage = controller.getViewState().pathToWallpaper,
                        onClick = ::onWallpaperAdd
                    )
                }
                SwitchLable(
                    modifier = Modifier.fillMaxWidth(),
                    label = "Competitive",
                    isChecked = controller.getViewState().isCompetitive,
                    onCheckedChange = ::onCompetitiveChange
                )
            }
            Row(
                modifier = Modifier.align(Alignment.BottomEnd),
                horizontalArrangement = spacedBy20dp
            ) {
                ButtonApp(
                    label = "clear",
                    color = greyAccent,
                    onClick = ::onClear
                )
                ButtonApp(
                    label = "submit",
                    color = orangeAccent,
                    onClick = ::onSubmit
                )
            }
        }
    }
}