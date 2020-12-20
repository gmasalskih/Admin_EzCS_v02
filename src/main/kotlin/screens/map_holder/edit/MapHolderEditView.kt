package screens.map_holder.edit

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

class MapHolderEditView(documentName: String) : BaseView<MapHolderEditController>() {
    override val controller by inject<MapHolderEditController>()

    init {
        controller.setDocumentName(documentName)
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
                    CardImageUrl(
                        label = "add logo",
                        pathToFile = controller.getViewState().item.logo,
                        onClick = controller::onLogoChange
                    )
                    CardImageUrl(
                        label = "add map",
                        pathToFile = controller.getViewState().item.map,
                        onClick = controller::onMapChange
                    )
                    CardImageUrl(
                        label = "add wallpaper",
                        pathToFile = controller.getViewState().item.wallpaper,
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
            Row(
                modifier = Modifier.align(Alignment.BottomEnd),
                horizontalArrangement = spacedBy20dp
            ) {
                ButtonApp(
                    label = "delete",
                    color = greyAccent,
                    onClick = controller::onDelete,
                )
                ButtonApp(
                    label = "submit",
                    color = orangeAccent,
                    onClick = controller::onSubmit,
                )
            }
        }
    }
}