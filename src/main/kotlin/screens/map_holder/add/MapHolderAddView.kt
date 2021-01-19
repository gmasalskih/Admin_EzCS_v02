package screens.map_holder.add

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import common_widgets.*
import org.koin.core.component.inject
import screens.BaseView
import ui.*

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
                TextFieldApp(
                    value = controller.viewState.item.name,
                    label = "Enter map name",
                    onTextChanged = controller::onNameChange
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = spacedBy20dp
                ) {
                    CardAddOrImage(
                        label = "add logo",
                        pathToFile = controller.viewState.item.logo,
                        onClick = controller::onLogoAdd
                    )
                    CardAddOrImage(
                        label = "add map",
                        pathToFile = controller.viewState.item.map,
                        onClick = controller::onMapAdd
                    )
                    CardAddOrImage(
                        label = "add wallpaper",
                        pathToFile = controller.viewState.item.wallpaper,
                        onClick = controller::onWallpaperAdd
                    )
                }
                SwitchLable(
                    modifier = Modifier.fillMaxWidth(),
                    label = "Competitive",
                    isChecked = controller.viewState.item.isCompetitive,
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
                    isActive = controller.viewState.item.isValid(),
                    color = orangeAccent,
                    onClick = controller::onSubmit
                )
            }
        }
    }
}