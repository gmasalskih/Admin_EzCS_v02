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
import ui.spacedBy20dp

class MapHolderAddView : BaseView<MapHolderAddController>() {
    override val controller by inject<MapHolderAddController>()

    @Composable
    override fun setContent(controller: MapHolderAddController) {
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
                        value = controller.getViewState().item.id,
                        label = "Enter map ID",
                        onTextChanged = controller::onIdChange
                    )
                    //Map name
                    TextFieldApp(
                        value = controller.getViewState().item.name,
                        label = "Enter map name",
                        onTextChanged = controller::onNameChange
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = spacedBy20dp
                ) {
                    CardAddOrImage(
                        label = "add logo",
                        pathToImage = controller.getViewState().item.logo,
                        onClick = controller::onLogoAdd
                    )
                    CardAddOrImage(
                        label = "add map",
                        pathToImage = controller.getViewState().item.map,
                        onClick = controller::onMapAdd
                    )
                    CardAddOrImage(
                        label = "add wallpaper",
                        pathToImage = controller.getViewState().item.wallpaper,
                        onClick = controller::onWallpaperAdd
                    )
                }
                SwitchLable(
                    modifier = Modifier.fillMaxWidth(),
                    label = "Competitive",
                    isChecked = controller.getViewState().item.isCompetitive,
                    onCheckedChange = controller::onCompetitiveChange
                )
            }
            Row(
                modifier = Modifier.align(Alignment.BottomEnd),
                horizontalArrangement = spacedBy20dp
            ) {
                ButtonApp(
                    label = "clear",
                    color = greyAccent,
                    onClick = controller::onClear
                )
                ButtonApp(
                    label = "submit",
                    color = orangeAccent,
                    onClick = controller::onSubmit
                )
            }
        }
    }
}