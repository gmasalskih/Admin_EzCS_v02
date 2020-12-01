package screens.map_points.edit

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import common_widgets.*
import org.koin.core.inject
import screens.BaseView
import ui.orangeAccent
import ui.spacedBy20dp

class MapPointsEditView(val id: String) : BaseView<MapPointsEditController>() {
    override val controller by inject<MapPointsEditController>()

    @Composable
    override fun setContent(controller: MapPointsEditController) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            ScrollableColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = spacedBy20dp
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = spacedBy20dp
                ) {
                    TextFieldApp(
                        value = controller.getViewState().item.mapId,
                        label = "Map ID",
                        onTextChanged = {}
                    )
                    TextFieldApp(
                        value = controller.getViewState().item.name,
                        label = "Map point name",
                        onTextChanged = controller::onNameChange
                    )
                }
                RadioGroupGrenadeTypes(
                    onTypeSelected = controller::onGrenadeTypeChange,
                    grenadeTypeSelected = controller.getViewState().item.grenadeType
                )
                CheckboxGroupTickrateTypes(
                    listTickrateTypes = controller.getViewState().item.tickRate,
                    onTickrateTypeClick = controller::onTickrateChange
                )
                Row(
                    horizontalArrangement = spacedBy20dp
                ) {
                    CardImage(
                        onClick = controller::onPreviewStartChange,
                        pathToFile = controller.getViewState().item.previewStart
                    )
                    CardImage(
                        onClick = controller::onPreviewEndChange,
                        pathToFile = controller.getViewState().item.previewEnd
                    )
                }
                ScrollableRowAdd(
                    modifier = Modifier.fillMaxWidth(),
                    items = controller.getViewState().item.contentVideos,
                    cardAdd = {
                        CardAdd(
                            label = "Add video",
                            onClick = controller::onVideoAdd
                        )
                    },
                    cardItem = { fullPathToVideo ->
                        CardImage(
                            pathToFile = fullPathToVideo,
                            onClick = { controller.onVideoChange(fullPathToVideo) },
                            onClickDel = { controller.onVideoDelete(fullPathToVideo) }
                        )
                    }
                )
                ScrollableRowAdd(
                    modifier = Modifier.fillMaxWidth(),
                    items = controller.getViewState().item.contentImages,
                    cardAdd = {
                        CardAdd(
                            label = "Add image",
                            onClick = controller::onImageAdd
                        )
                    },
                    cardItem = { fullPathToImage ->
                        CardImage(
                            pathToFile = fullPathToImage,
                            onClick = { controller.onImageChange(fullPathToImage) },
                            onClickDel = { controller.onImageDelete(fullPathToImage) }
                        )
                    }
                )
            }
            ButtonApp(
                modifier = Modifier.align(Alignment.BottomEnd),
                label = "submit",
                color = orangeAccent,
                onClick = controller::onSubmit
            )
        }
    }
}