package screens.map_point.edit

import androidx.compose.runtime.*
import data.entitys.MapPoint
import data.types.ContentSourceType
import data.types.FileType
import screens.BaseEditController
import screens.ViewState
import utils.fileChooser

class MapPointEditController : BaseEditController<MapPoint, MapPointEditItemViewState>() {

    override val defaultItemViewState: MapPointEditItemViewState = MapPointEditItemViewState()

    override var viewState: ViewState<MapPointEditItemViewState> by mutableStateOf(
        ViewState(
            title = "Edit map point",
            item = defaultItemViewState
        )
    )

    fun onPreviewStartChange() {
        fileChooser("Select preview start", FileType.PNG, viewState.item.previewStart) { newPreviewStart ->
            setItemViewState(
                viewState.item.copy(
                    previewStart = newPreviewStart
                )
            )
        }
    }

    fun onPreviewEndChange() {
        fileChooser("Select preview end", FileType.PNG, viewState.item.previewEnd) { newPreviewEnd ->
            setItemViewState(
                viewState.item.copy(
                    previewEnd = newPreviewEnd
                )
            )
        }
    }

    fun onVideoAdd() {
        fileChooser("Select video", FileType.MP4, viewState.item.contentVideos) { newVideo ->
            setItemViewState(
                viewState.item.copy(
                    contentVideos = viewState.item.contentVideos + listOf(newVideo)
                )
            )
        }
    }

    fun onVideoChange(oldVideo: ContentSourceType) {
        fileChooser("Select video", FileType.MP4, viewState.item.contentVideos) { newVideo ->
            setItemViewState(
                viewState.item.copy(
                    contentVideos = viewState.item.contentVideos.map { video ->
                        if (video == oldVideo) newVideo else video
                    }
                )
            )
        }
    }

    fun onImageAdd() {
        fileChooser("Select image", FileType.PNG, viewState.item.contentImages) { newImage ->
            setItemViewState(
                viewState.item.copy(
                    contentImages = viewState.item.contentImages + listOf(newImage)
                )
            )
        }
    }

    fun onImageChange(oldImage: ContentSourceType) {
        fileChooser("Select image", FileType.PNG, viewState.item.contentImages) { newImage ->
            setItemViewState(
                viewState.item.copy(
                    contentImages = viewState.item.contentImages.map { image ->
                        if (image == oldImage) newImage else image
                    }
                )
            )
        }
    }

    fun onImageDelete(image: ContentSourceType) {
        setItemViewState(
            viewState.item.copy(
                contentImages = viewState.item.contentImages.filter { it != image }
            )
        )
    }

    fun onVideoDelete(video: ContentSourceType) {
        setItemViewState(
            viewState.item.copy(
                contentVideos = viewState.item.contentVideos.filter { it != video }
            )
        )
    }

    fun onDelete() {
        launchDeletingEntityOnServer()
    }

    fun onSubmit() {
        launchUpdatingEntityOnServer(viewState.item)
    }

    override suspend fun setEntity() {
        service.getEntityAsync(documentName, MapPoint::class).await().let { mapPoint ->
            viewState = viewState.copy(title = mapPoint.getDocumentName().substringAfter("/"))
            setItemViewState(
                MapPointEditItemViewState(
                    name = mapPoint.name,
                    mapDocumentName = mapPoint.mapDocumentName,
                    grenadeType = mapPoint.grenadeType,
                    tickrateTypes = mapPoint.tickrateTypes,
                    previewStart = ContentSourceType.ContentStorageThumbnail(
                        mapPoint.getDocumentName(),
                        mapPoint.previewStart
                    ),
                    previewEnd = ContentSourceType.ContentStorageThumbnail(
                        mapPoint.getDocumentName(),
                        mapPoint.previewEnd
                    ),
                    contentImages = mapPoint.contentImages.map { img ->
                        ContentSourceType.ContentStorageThumbnail(
                            mapPoint.getDocumentName(),
                            img
                        )
                    },
                    contentVideos = mapPoint.contentVideos.map { video ->
                        ContentSourceType.ContentStorageThumbnail(
                            mapPoint.getDocumentName(),
                            video
                        )
                    }
                )
            )
        }
    }

    override fun mapper(itemViewState: MapPointEditItemViewState): MapPoint {
        return MapPoint(
            name = itemViewState.name,
            mapDocumentName = itemViewState.mapDocumentName,
            grenadeType = itemViewState.grenadeType,
            tickrateTypes = itemViewState.tickrateTypes,
            previewStart = itemViewState.previewStart.value,
            previewEnd = itemViewState.previewEnd.value,
            contentImages = itemViewState.contentImages.map { it.value },
            contentVideos = itemViewState.contentVideos.map { it.value }
        )
    }
}