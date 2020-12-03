package screens.map_holder.edit

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import common_widgets.*
import org.koin.core.inject
import screens.BaseView
import ui.orangeAccent
import ui.spacedBy20dp

class MapHolderEditView(val id: String) : BaseView<MapHolderEditController>() {
    override val controller by inject<MapHolderEditController>()

    init {
//        controller.setId(id)
    }

    @Composable
    override fun setContent(controller: MapHolderEditController) {
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
                        value = controller.getViewState().item.getId(),
                        label = "Map ID",
                    )
                    //Map name
                    TextFieldApp(
                        value = controller.getViewState().item.name,
                        label = "Map name",
                        onTextChanged = controller::onNameChange
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = spacedBy20dp
                ) {

                    CardAddOrImage(
                        label = "add logo",
                        image = controller.getViewState().item.logo,
                        onClick = controller::onLogoChange
                    )
                    CardAddOrImage(
                        label = "add map",
                        image = controller.getViewState().item.map,
                        onClick = controller::onMapChange
                    )
                    CardAddOrImage(
                        label = "add wallpaper",
                        image = controller.getViewState().item.wallpaper,
                        onClick = controller::onWallpaperChange
                    )
                }
                SwitchLable(
                    label = "Competitive",
                    isChecked = controller.getViewState().item.isCompetitive,
                    modifier = Modifier.fillMaxWidth(),
                    onCheckedChange = controller::onCompetitiveChange
                )
            }
            ButtonApp(
                label = "submit",
                color = orangeAccent,
                onClick = controller::onSubmit,
                modifier = Modifier.align(Alignment.BottomEnd)
            )
        }
    }
}