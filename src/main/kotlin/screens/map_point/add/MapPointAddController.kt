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

class MapPointAddController : BaseAddController<MapPointAddState>() {

    override val defaultItemState: MapPointAddState = MapPointAddState()

    var listMapHolder: List<MapHolder> by mutableStateOf(listOf())
        private set
    var selectedMapHolder: MapHolder? by mutableStateOf(null)
        private set

    override var state: ViewState<MapPointAddState> by mutableStateOf(
        ViewState(
            title = "New map point",
            item = MapPointAddState()
        )
    )

    fun onMapHolderSelect(mapHolder: MapHolder) {
        selectedMapHolder = mapHolder
        setItemState(
            state.item.copy(
                mapDocumentName = mapHolder.getDocumentName()
            )
        )
    }

    fun onNameChange(name: String) {
        setItemState(
            state.item.copy(
                name = name.toValidName()
            )
        )
    }

    fun onGrenadeTypeChange(grenadeType: GrenadeType) {
        setItemState(
            state.item.copy(
                grenadeType = grenadeType
            )
        )
    }

    fun onTickrateChange(tickrate: TickrateType) {
        setItemState(
            state.item.copy(
                tickrateTypes = if (state.item.tickrateTypes.contains(tickrate)) {
                    state.item.tickrateTypes.filter { it != tickrate }
                } else {
                    state.item.tickrateTypes + listOf(tickrate)
                }
            )
        )
    }

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

    fun onClear(){
        selectedMapHolder = null
        setDefaultState()
    }

    fun onSubmit() {
        launchUploadingEntityOnServer(state.item)
        selectedMapHolder = null
    }

    override suspend fun upload(stateItem: MapPointAddState) {
        service.uploadEntity(
            MapPoint(
                name = stateItem.name,
                mapDocumentName = stateItem.mapDocumentName,
                grenadeType = stateItem.grenadeType,
                tickrateTypes = stateItem.tickrateTypes,
                previewStart = stateItem.previewStart.value,
                previewEnd = stateItem.previewEnd.value,
                contentImages = stateItem.contentImages.map { it.value },
                contentVideos = stateItem.contentVideos.map { it.value },
            )
        )
    }

    override fun onViewCreate() {
        super.onViewCreate()
        controllerScope.launch { listMapHolder = service.getListEntities(EntityType.MAP_HOLDER.name, MapHolder::class) }
    }
}