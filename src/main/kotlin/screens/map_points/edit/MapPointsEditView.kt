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
                        label = "Map ID",
                        onTextChanged = {}
                    )
                    TextFieldApp(
                        value = controller.getViewState().mapPointName,
                        label = "Map point name",
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
                    CardImage(
                        onClick = ::onSelectPreviewStart,
                        pathToFile = controller.getViewState().pathToPreviewStartImg
                    )
                    CardImage(
                        onClick = ::onSelectPreviewEnd,
                        pathToFile = controller.getViewState().pathToPreviewEndImg
                    )
                }
                ScrollableRowAdd(
                    modifier = Modifier.fillMaxWidth(),
                    items = controller.getViewState().listVideos,
                    cardAdd = {
                        CardAdd(
                            label = "Add video",
                            onClick = ::onClickAddVideo
                        )
                    },
                    cardItem = { fullPathToVideo ->
                        CardImage(
                            pathToFile = fullPathToVideo,
                            onClick = { onClickVideo(fullPathToVideo) },
                            onClickDel = { onDeleteVideo(fullPathToVideo) }
                        )
                    }
                )
                ScrollableRowAdd(
                    modifier = Modifier.fillMaxWidth(),
                    items = controller.getViewState().listImages,
                    cardAdd = {
                        CardAdd(
                            label = "Add image",
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
            ButtonApp(
                modifier = Modifier.align(Alignment.BottomEnd),
                label = "submit",
                color = orangeAccent,
                onClick = ::submitBtnClick
            )
        }
    }
}