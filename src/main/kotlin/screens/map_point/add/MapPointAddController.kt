package screens.map_point.add

import androidx.compose.runtime.*
import data.types.GrenadeType
import data.types.TickrateType
import data.entitys.MapPoint
import screens.BaseController
import screens.ViewState
import utils.fileChooser

class MapPointAddController : BaseController<MapPointAddState>() {
    override var state: ViewState<MapPointAddState> by mutableStateOf(ViewState(title = "New map point", item = MapPointAddState()))

    fun onClear() {
        state = ViewState(title = "New map point", item = MapPointAddState())
    }

    fun onMapIdChange(mapId: String) = setItemState(state.item.copy(mapId = mapId))
    fun onNameChange(name: String) = setItemState(state.item.copy(name = name))
    fun onGrenadeTypeChange(grenadeType: GrenadeType) = setItemState(state.item.copy(grenadeType = grenadeType))
    fun onTickrateChange(tickrate: TickrateType) =
        setItemState(state.item.copy(
            tickrateTypes = if (state.item.tickrateTypes.contains(tickrate)) state.item.tickrateTypes.filter { it != tickrate }
            else state.item.tickrateTypes + listOf(tickrate)
        ))

    fun onPreviewStartChange() {
        val newImage = fileChooser("Select preview start", "png") ?: return
        setItemState(state.item.copy(previewStart = newImage))
    }

    fun onPreviewEndChange() {
        val newImage = fileChooser("Select preview end", "png") ?: return
        setItemState(state.item.copy(previewEnd = newImage))
    }

    fun onVideoAdd() {
        val newVideo = fileChooser("Select video", "mp4") ?: return
        if (!state.item.contentVideos.contains(newVideo))
            setItemState(state.item.copy(contentVideos = state.item.contentVideos + listOf(newVideo)))
    }

    fun onVideoChange(oldVideo: String) {
        val newVideo = fileChooser("Select video", "mp4") ?: return
        if (!state.item.contentVideos.contains(newVideo))
            setItemState(state.item.copy(contentVideos = state.item.contentVideos.map { video -> if (video == oldVideo) newVideo else video }))
    }

    fun onImageAdd() {
        val newImage = fileChooser("Select image", "png") ?: return
        if (!state.item.contentImages.contains(newImage))
            setItemState(state.item.copy(contentImages = state.item.contentImages + listOf(newImage)))
    }

    fun onImageChange(oldImage: String) {
        val newImage = fileChooser("Select image", "png") ?: return
        if (!state.item.contentImages.contains(newImage))
            setItemState(state.item.copy(contentImages = state.item.contentImages.map { image -> if (image == oldImage) newImage else image }))
    }

    fun onImageDelete(image: String) =
        setItemState(state.item.copy(contentImages = state.item.contentImages.filter { it != image }))

    fun onVideoDelete(video: String) =
        setItemState(state.item.copy(contentVideos = state.item.contentVideos.filter { it != video }))

    fun onSubmit() {
//        TODO("Not yet implemented")
    }

    override fun initState() {
//        TODO("Not yet implemented")
    }
}