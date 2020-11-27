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
import data.enums.GrenadeTypes
import data.enums.TickRate
import org.koin.core.inject
import screens.BaseView
import ui.orangeAccent
import utils.fileChooser
import utils.toValidName

class MapPointsEditView(val id: String) : BaseView<MapPointsEditController>() {
    override val controller by inject<MapPointsEditController>()

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
                        label = "Map ID",
                        onTextChanged = {}
                    )
                    TextFieldApp(
                        value = controller.getViewState().name,
                        label = "Map point name",
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
                    CardImage(
                        onClick = ::onPreviewStartChange,
                        pathToFile = controller.getViewState().pathToPreviewStart
                    )
                    CardImage(
                        onClick = ::onPreviewEndChange,
                        pathToFile = controller.getViewState().pathToPreviewEnd
                    )
                }
                ScrollableRowAdd(
                    modifier = Modifier.fillMaxWidth(),
                    items = controller.getViewState().listVideos,
                    cardAdd = {
                        CardAdd(
                            label = "Add video",
                            onClick = ::onVideoAdd
                        )
                    },
                    cardItem = { fullPathToVideo ->
                        CardImage(
                            pathToFile = fullPathToVideo,
                            onClick = { onVideoChange(fullPathToVideo) },
                            onClickDel = { onVideoDelete(fullPathToVideo) }
                        )
                    }
                )
                ScrollableRowAdd(
                    modifier = Modifier.fillMaxWidth(),
                    items = controller.getViewState().listImages,
                    cardAdd = {
                        CardAdd(
                            label = "Add image",
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
            ButtonApp(
                modifier = Modifier.align(Alignment.BottomEnd),
                label = "submit",
                color = orangeAccent,
                onClick = ::onSubmit
            )
        }
    }
}