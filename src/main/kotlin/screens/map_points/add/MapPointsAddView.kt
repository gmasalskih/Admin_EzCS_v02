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

    private fun onMapIdChanged(mapId: String) {
        controller.setViewState(
            controller.getViewState().copy(
                mapId = mapId.toValidId()
            )
        )
    }

    private fun onMapPointNameChanged(mapPointName: String) {
        controller.setViewState(
            controller.getViewState().copy(
                mapPointName = mapPointName.toValidName()
            )
        )
    }

    private fun onGrenadeTypeSelected(grenadeType: GrenadeTypes) {
        controller.setViewState(
            controller.getViewState().copy(
                grenadeType = grenadeType
            )
        )
    }

    private fun onTickrateTypeClick(tickRate: TickRate) {
        val hasTickRate = controller.getViewState().listTickRates.contains(tickRate)
        controller.setViewState(
            controller.getViewState().copy(
                listTickRates = if (hasTickRate) controller.getViewState().listTickRates.filter { it != tickRate }
                else controller.getViewState().listTickRates + listOf(tickRate)
            )
        )
    }

    private fun onSelectPreviewStart() {
        val pathToImage = fileChooser("Select preview start", "png") ?: return
        controller.setViewState(
            controller.getViewState().copy(
                pathToPreviewStartImg = pathToImage
            )
        )
    }

    private fun onSelectPreviewEnd() {
        val pathToImage = fileChooser("Select preview end", "png") ?: return
        controller.setViewState(
            controller.getViewState().copy(
                pathToPreviewEndImg = pathToImage
            )
        )
    }

    private fun onClickAddVideo() {
        val pathToVideo = fileChooser("Select video", "mp4") ?: return
        if (!controller.getViewState().listVideos.contains(pathToVideo)) {
            controller.setViewState(
                controller.getViewState().copy(
                    listVideos = controller.getViewState().listVideos + listOf(pathToVideo)
                )
            )
        }
    }

    private fun onClickVideo(oldPathToVideo: String) {
        val newPathToVideo = fileChooser("Select video", "mp4") ?: return
        if (!controller.getViewState().listVideos.contains(newPathToVideo)) {
            controller.setViewState(
                controller.getViewState().copy(
                    listVideos = controller.getViewState().listVideos.map {
                        if (it == oldPathToVideo) newPathToVideo else it
                    }.toList()
                )
            )
        }
    }

    private fun onClickAddImage() {
        val pathToImage = fileChooser("Select image", "png") ?: return
        if (!controller.getViewState().listImages.contains(pathToImage)) {
            controller.setViewState(
                controller.getViewState().copy(
                    listImages = controller.getViewState().listImages + listOf(pathToImage)
                )
            )
        }
    }

    private fun onClickImage(oldPathToImage: String) {
        val newPathToImage = fileChooser("Select image", "png") ?: return
        if (!controller.getViewState().listImages.contains(newPathToImage)) {
            controller.setViewState(
                controller.getViewState().copy(
                    listImages = controller.getViewState().listImages.map {
                        if (it == oldPathToImage) newPathToImage else it
                    }.toList()
                )
            )
        }
    }

    private fun onDeleteImage(pathToImage: String) {
        controller.setViewState(
            controller.getViewState().copy(
                listImages = controller.getViewState().listImages.filter { it != pathToImage }
            )
        )
    }

    private fun onDeleteVideo(pathToVideo: String) {
        controller.setViewState(
            controller.getViewState().copy(
                listVideos = controller.getViewState().listVideos.filter { it != pathToVideo }
            )
        )
    }

    private fun clearBtnClick() {
        controller.clearState()
    }

    private fun submitBtnClick() {
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
                        onTextChanged = ::onMapIdChanged
                    )
                    TextFieldApp(
                        value = controller.getViewState().mapPointName,
                        label = "Enter map point name",
                        onTextChanged = ::onMapPointNameChanged
                    )
                }
                RadioGroupGrenadeTypes(
                    onTypeSelected = ::onGrenadeTypeSelected,
                    grenadeTypeSelected = controller.getViewState().grenadeType
                )
                CheckboxGroupTickrateTypes(
                    listTickrateTypes = controller.getViewState().listTickRates,
                    onTickrateTypeClick = ::onTickrateTypeClick
                )
                Row(
                    horizontalArrangement = spacedBy20dp
                ) {
                    CardAddOrImage(
                        label = "add preview start",
                        onClick = ::onSelectPreviewStart,
                        pathToImage = controller.getViewState().pathToPreviewStartImg
                    )
                    CardAddOrImage(
                        label = "add preview end",
                        onClick = ::onSelectPreviewEnd,
                        pathToImage = controller.getViewState().pathToPreviewEndImg
                    )
                }
                ScrollableRowAdd(
                    modifier = Modifier.fillMaxWidth(),
                    items = controller.getViewState().listVideos,
                    cardAdd = {
                        CardAdd(
                            label = "add video",
                            onClick = ::onClickAddVideo
                        )
                    },
                    cardItem = { fullPathToVideo ->
                        CardImage(
                            pathToFile = fullPathToVideo,
                            onClickDel = { onDeleteVideo(fullPathToVideo) },
                            onClick = { onClickVideo(fullPathToVideo) }
                        )
                    }
                )
                ScrollableRowAdd(
                    modifier = Modifier.fillMaxWidth(),
                    items = controller.getViewState().listImages,
                    cardAdd = {
                        CardAdd(
                            label = "add image",
                            onClick = ::onClickAddImage
                        )
                    },
                    cardItem = { fullPathToImage ->
                        CardImage(
                            pathToFile = fullPathToImage,
                            onClick = { onClickImage(fullPathToImage) },
                            onClickDel = { onDeleteImage(fullPathToImage) }
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
                    onClick = ::clearBtnClick
                )
                ButtonApp(
                    label = "submit",
                    color = orangeAccent,
                    onClick = ::submitBtnClick
                )
            }
        }
    }
}