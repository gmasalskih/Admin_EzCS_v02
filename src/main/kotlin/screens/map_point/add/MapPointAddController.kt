package screens.map_point.add

import androidx.compose.runtime.*
import data.entitys.MapHolder
import data.entitys.MapPoint
import data.types.*
import kotlinx.coroutines.launch
import screens.BaseAddController
import screens.ViewState
import utils.fileChooser
import utils.toValidName

class MapPointAddController : BaseAddController<MapPoint, MapPointAddItemViewState>() {

    override val defaultItemViewState: MapPointAddItemViewState = MapPointAddItemViewState()

    var listMapHolder: List<MapHolder> by mutableStateOf(listOf())
        private set
    var selectedMapHolder: MapHolder? by mutableStateOf(null)
        private set

    override var viewState: ViewState<MapPointAddItemViewState> by mutableStateOf(
        ViewState(
            title = "New map point",
            item = MapPointAddItemViewState()
        )
    )

    fun onMapHolderSelect(mapHolder: MapHolder) {
        selectedMapHolder = mapHolder
        setItemViewState(
            viewState.item.copy(
                mapDocumentName = mapHolder.getDocumentName()
            )
        )
    }

    fun onNameChange(name: String) {
        setItemViewState(
            viewState.item.copy(
                name = name.toValidName()
            )
        )
    }

    fun onGrenadeTypeChange(grenadeType: GrenadeType) {
        setItemViewState(
            viewState.item.copy(
                grenadeType = grenadeType
            )
        )
    }

    fun onTickrateChange(tickrate: TickrateType) {
        setItemViewState(
            viewState.item.copy(
                tickrateTypes = if (viewState.item.tickrateTypes.contains(tickrate)) {
                    viewState.item.tickrateTypes.filter { it != tickrate }
                } else {
                    viewState.item.tickrateTypes + listOf(tickrate)
                }
            )
        )
    }

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

    fun onClear() {
        selectedMapHolder = null
        setDefaultState()
    }

    fun onSubmit() {
        launchUploadingEntityOnServer(viewState.item)
        selectedMapHolder = null
    }

    override fun onViewCreate() {
        super.onViewCreate()
        controllerScope.launch {
            listMapHolder = service.getListEntitiesAsync(EntityType.MAP_HOLDER.name, MapHolder::class).await()
        }
    }

    override fun convertItemViewSateToEntity(itemViewState: MapPointAddItemViewState): MapPoint {
        return MapPoint(
            name = itemViewState.name,
            mapDocumentName = itemViewState.mapDocumentName,
            grenadeType = itemViewState.grenadeType,
            tickrateTypes = itemViewState.tickrateTypes,
            previewStart = itemViewState.previewStart.value,
            previewEnd = itemViewState.previewEnd.value,
            contentImages = itemViewState.contentImages.map { it.value },
            contentVideos = itemViewState.contentVideos.map { it.value },
        )
    }
}