package screens.map_points.add

import androidx.compose.foundation.ScrollableColumn
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

class MapPointsAddView : BaseView<MapPointsAddController>() {
    override val controller by inject<MapPointsAddController>()

    @Composable
    override fun setContent(controller: MapPointsAddController) {
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
                        label = "Enter map ID",
                        onTextChanged = controller::onMapIdChange
                    )
                    TextFieldApp(
                        value = controller.getViewState().item.name,
                        label = "Enter map point name",
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
                    CardAddOrImage(
                        label = "add preview start",
                        onClick = controller::onPreviewStartChange,
                        pathToImage = controller.getViewState().item.previewStart
                    )
                    CardAddOrImage(
                        label = "add preview end",
                        onClick = controller::onPreviewEndChange,
                        pathToImage = controller.getViewState().item.previewEnd
                    )
                }
                ScrollableRowAdd(
                    modifier = Modifier.fillMaxWidth(),
                    items = controller.getViewState().item.contentVideos,
                    cardAdd = {
                        CardAdd(
                            label = "add video",
                            onClick = controller::onVideoAdd
                        )
                    },
                    cardItem = { fullPathToVideo ->
                        CardImage(
                            pathToFile = fullPathToVideo,
                            onClickDel = { controller.onVideoDelete(fullPathToVideo) },
                            onClick = { controller.onVideoChange(fullPathToVideo) }
                        )
                    }
                )
                ScrollableRowAdd(
                    modifier = Modifier.fillMaxWidth(),
                    items = controller.getViewState().item.contentImages,
                    cardAdd = {
                        CardAdd(
                            label = "add image",
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