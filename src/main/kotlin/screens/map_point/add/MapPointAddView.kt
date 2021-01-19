package screens.map_point.add

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import common_widgets.*
import data.types.ContentSourceType
import org.koin.core.component.inject
import screens.BaseView
import ui.greyAccent
import ui.orangeAccent
import ui.spacedBy20dp
import ui.spacedBy40dp

class MapPointAddView : BaseView<MapPointAddController>() {
    override val controller by inject<MapPointAddController>()

    @Composable
    override fun setContent(controller: MapPointAddController) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            ScrollableColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = spacedBy20dp
            ) {
                TextFieldApp(
                    value = controller.viewState.item.name,
                    label = "Enter map point name",
                    onTextChanged = controller::onNameChange
                )
                ScrollableItemRow(
                    modifier = Modifier.fillMaxWidth(),
                    items = controller.listMapHolder
                ) { mapHolder ->
                    CardMapHolder(
                        background = ContentSourceType.ContentStorageThumbnail(
                            mapHolder.getDocumentName(),
                            mapHolder.wallpaper
                        ),
                        isSelected = mapHolder === controller.selectedMapHolder,
                        logo = ContentSourceType.ContentStorageOriginal(mapHolder.getDocumentName(), mapHolder.logo),
                        name = mapHolder.name,
                        isCompetitive = mapHolder.isCompetitive,
                        onClick = { controller.onMapHolderSelect(mapHolder) }
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = spacedBy40dp,
                ) {
                    RadioGroupGrenadeTypes(
                        onTypeSelected = controller::onGrenadeTypeChange,
                        grenadeTypeSelected = controller.viewState.item.grenadeType
                    )
                    CheckboxGroupTickrateTypes(
                        listTickrateTypes = controller.viewState.item.tickrateTypes,
                        onTickrateTypeClick = controller::onTickrateChange
                    )
                }
                Row(
                    horizontalArrangement = spacedBy20dp
                ) {
                    CardAddOrImage(
                        label = "add preview start",
                        onClick = controller::onPreviewStartChange,
                        pathToFile = controller.viewState.item.previewStart
                    )
                    CardAddOrImage(
                        label = "add preview end",
                        onClick = controller::onPreviewEndChange,
                        pathToFile = controller.viewState.item.previewEnd
                    )
                }
                ScrollableAddRow(
                    modifier = Modifier.fillMaxWidth(),
                    items = controller.viewState.item.contentVideos,
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
                ScrollableAddRow(
                    modifier = Modifier.fillMaxWidth(),
                    items = controller.viewState.item.contentImages,
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
                    isActive = controller.viewState.item.isValid(),
                    color = orangeAccent,
                    onClick = controller::onSubmit
                )
            }
        }
    }
}