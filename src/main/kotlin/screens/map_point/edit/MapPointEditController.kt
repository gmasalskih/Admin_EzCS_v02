package screens.map_point.edit

import androidx.compose.runtime.*
import data.entitys.MapPoint
import data.types.ContentSourceType
import data.types.FileType
import screens.BaseEditController
import screens.ViewState
import utils.fileChooser

class MapPointEditController : BaseEditController<MapPointEditState>() {

    override val defaultItemState: MapPointEditState = MapPointEditState()

    override var state: ViewState<MapPointEditState> by mutableStateOf(
        ViewState(
            title = "Edit map point",
            item = defaultItemState
        )
    )

    fun onPreviewStartChange() {
        fileChooser("Select preview start", FileType.PNG, state.item.previewStart) { newPreviewStart ->
            setItemState(
                state.item.copy(
                    previewStart = newPreviewStart
                )
            )
        }
    }

    fun onPreviewEndChange() {
        fileChooser("Select preview end", FileType.PNG, state.item.previewEnd) { newPreviewEnd ->
            setItemState(
                state.item.copy(
                    previewEnd = newPreviewEnd
                )
            )
        }
    }

    fun onVideoAdd() {
        fileChooser("Select video", FileType.MP4, state.item.contentVideos) { newVideo ->
            setItemState(
                state.item.copy(
                    contentVideos = state.item.contentVideos + listOf(newVideo)
                )
            )
        }
    }

    fun onVideoChange(oldVideo: ContentSourceType) {
        fileChooser("Select video", FileType.MP4, state.item.contentVideos) { newVideo ->
            setItemState(
                state.item.copy(
                    contentVideos = state.item.contentVideos.map { video ->
                        if (video == oldVideo) newVideo else video
                    }
                )
            )
        }
    }

    fun onImageAdd() {
        fileChooser("Select image", FileType.PNG, state.item.contentImages) { newImage ->
            setItemState(
                state.item.copy(
                    contentImages = state.item.contentImages + listOf(newImage)
                )
            )
        }
    }

    fun onImageChange(oldImage: ContentSourceType) {
        fileChooser("Select image", FileType.PNG, state.item.contentImages) { newImage ->
            setItemState(
                state.item.copy(
                    contentImages = state.item.contentImages.map { image ->
                        if (image == oldImage) newImage else image
                    }
                )
            )
        }
    }

    fun onImageDelete(image: ContentSourceType) {
        setItemState(
            state.item.copy(
                contentImages = state.item.contentImages.filter { it != image }
            )
        )
    }

    fun onVideoDelete(video: ContentSourceType) {
        setItemState(
            state.item.copy(
                contentVideos = state.item.contentVideos.filter { it != video }
            )
        )
    }

    fun onDelete() {
        launchDeletingEntityOnServer()
    }

    fun onSubmit() {
        launchUpdatingEntityOnServer(state.item)
    }

    override suspend fun setEntity() {
        service.getEntity(documentName, MapPoint::class).let { mapPoint ->
            state = state.copy(title = mapPoint.getDocumentName().substringAfter("/"))
            val mpes = MapPointEditState(
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
            println(mpes)
            setItemState(
                mpes
            )
        }
    }

    override suspend fun update(stateItem: MapPointEditState) {
        service.updateEntity(
            MapPoint(
                name = stateItem.name,
                mapDocumentName = stateItem.mapDocumentName,
                grenadeType = stateItem.grenadeType,
                tickrateTypes = stateItem.tickrateTypes,
                previewStart = stateItem.previewStart.value,
                previewEnd = stateItem.previewEnd.value,
                contentImages = stateItem.contentImages.map { it.value },
                contentVideos = stateItem.contentVideos.map { it.value }
            )
        )
    }
}