package screens.map_points.add

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import common_widgets.*
import data.enums.GrenadeTypes
import data.enums.TickRate
import org.koin.core.inject
import screens.BaseView
import ui.greyAccent
import ui.orangeAccent
import utils.fileChooser
import utils.toValidId
import utils.toValidName

class MapPointsAddView : BaseView<MapPointsAddController>() {
    override val controller by inject<MapPointsAddController>()

    private fun onMapIdChange(mapId: String) {
        controller.setViewState(
            controller.getViewState().copy(
                mapId = mapId.toValidId()
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

    private fun onGrenadeTypeChange(grenadeType: GrenadeTypes) {
        controller.setViewState(
            controller.getViewState().copy(
                grenadeType = grenadeType
            )
        )
    }

    private fun onTickrateTypeChange(tickrateType: TickRate) {
        val hasTickRate = controller.getViewState().listTickRates.contains(tickrateType)
        controller.setViewState(
            controller.getViewState().copy(
                listTickRates = if (hasTickRate) controller.getViewState().listTickRates.filter { it != tickrateType }
                else controller.getViewState().listTickRates + listOf(tickrateType)
            )
        )
    }

    private fun onPreviewStartChange() {
        val pathToImage = fileChooser("Select preview start", "png") ?: return
        controller.setViewState(
            controller.getViewState().copy(
                pathToPreviewStart = pathToImage
            )
        )
    }

    private fun onPreviewEndChange() {
        val pathToImage = fileChooser("Select preview end", "png") ?: return
        controller.setViewState(
            controller.getViewState().copy(
                pathToPreviewEnd = pathToImage
            )
        )
    }

    private fun onVideoAdd() {
        val pathToVideo = fileChooser("Select video", "mp4") ?: return
        if (!controller.getViewState().listVideos.contains(pathToVideo)) {
            controller.setViewState(
                controller.getViewState().copy(
                    listVideos = controller.getViewState().listVideos + listOf(pathToVideo)
                )
            )
        }
    }

    private fun onVideoChange(oldPathToVideo: String) {
        val newPathToVideo = fileChooser("Select video", "mp4") ?: return
        if (!controller.getViewState().listVideos.contains(newPathToVideo)) {
            controller.setViewState(
                controller.getViewState().copy(
                    listVideos = controller.getViewState().listVideos.map { pathToVideo ->
                        if (pathToVideo == oldPathToVideo) newPathToVideo else pathToVideo
                    }.toList()
                )
            )
        }
    }

    private fun onImageAdd() {
        val pathToImage = fileChooser("Select image", "png") ?: return
        if (!controller.getViewState().listImages.contains(pathToImage)) {
            controller.setViewState(
                controller.getViewState().copy(
                    listImages = controller.getViewState().listImages + listOf(pathToImage)
                )
            )
        }
    }

    private fun onImageChange(oldPathToImage: String) {
        val newPathToImage = fileChooser("Select image", "png") ?: return
        if (!controller.getViewState().listImages.contains(newPathToImage)) {
            controller.setViewState(
                controller.getViewState().copy(
                    listImages = controller.getViewState().listImages.map { pathToImage ->
                        if (pathToImage == oldPathToImage) newPathToImage else pathToImage
                    }.toList()
                )
            )
        }
    }

    private fun onImageDelete(pathToImage: String) {
        controller.setViewState(
            controller.getViewState().copy(
                listImages = controller.getViewState().listImages.filter { it != pathToImage }
            )
        )
    }

    private fun onVideoDelete(pathToVideo: String) {
        controller.setViewState(
            controller.getViewState().copy(
                listVideos = controller.getViewState().listVideos.filter { it != pathToVideo }
            )
        )
    }

    private fun onClear() {
        controller.clearState()
    }

    private fun onSubmit() {
        //TODO Сделать submitBtnClick
    }

    @Composable
    override fun setContent() {
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
                        value = controller.getViewState().mapId,
                        label = "Enter map ID",
                        onTextChanged = ::onMapIdChange
                    )
                    TextFieldApp(
                        value = controller.getViewState().name,
                        label = "Enter map point name",
                        onTextChanged = ::onNameChange
                    )
                }
                RadioGroupGrenadeTypes(
                    onTypeSelected = ::onGrenadeTypeChange,
                    grenadeTypeSelected = controller.getViewState().grenadeType
                )
                CheckboxGroupTickrateTypes(
                    listTickrateTypes = controller.getViewState().listTickRates,
                    onTickrateTypeClick = ::onTickrateTypeChange
                )
                Row(
                    horizontalArrangement = spacedBy20dp
                ) {
                    CardAddOrImage(
                        label = "add preview start",
                        onClick = ::onPreviewStartChange,
                        pathToImage = controller.getViewState().pathToPreviewStart
                    )
                    CardAddOrImage(
                        label = "add preview end",
                        onClick = ::onPreviewEndChange,
                        pathToImage = controller.getViewState().pathToPreviewEnd
                    )
                }
                ScrollableRowAdd(
                    modifier = Modifier.fillMaxWidth(),
                    items = controller.getViewState().listVideos,
                    cardAdd = {
                        CardAdd(
                            label = "add video",
                            onClick = ::onVideoAdd
                        )
                    },
                    cardItem = { fullPathToVideo ->
                        CardImage(
                            pathToFile = fullPathToVideo,
                            onClickDel = { onVideoDelete(fullPathToVideo) },
                            onClick = { onVideoChange(fullPathToVideo) }
                        )
                    }
                )
                ScrollableRowAdd(
                    modifier = Modifier.fillMaxWidth(),
                    items = controller.getViewState().listImages,
                    cardAdd = {
                        CardAdd(
                            label = "add image",
                            onClick = ::onImageAdd
                        )
                    },
                    cardItem = { fullPathToImage ->
                        CardImage(
                            pathToFile = fullPathToImage,
                            onClick = { onImageChange(fullPathToImage) },
                            onClickDel = { onImageDelete(fullPathToImage) }
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