package screens.map_points.edit

import androidx.compose.runtime.*
import data.enums.GrenadeTypes
import data.enums.TickRate
import data.pojo.MapPoint
import screens.BaseController
import screens.ViewState
import utils.fileChooser

class MapPointsEditController : BaseController<MapPoint>() {
    override var state: ViewState<MapPoint> by mutableStateOf(
        ViewState(
            title = "New map point", item = MapPoint(
                mapId = "id_palas_to_under_palas",
                name = "palas_to_under_palas",
                grenadeType = GrenadeTypes.SMOKE,
                tickRate = listOf(TickRate.TICKRATE_64),
                previewStart = "D:/EzCS/map_points/palas_to_under_palas_start.png",
                previewEnd = "D:/EzCS/map_points/palas_to_under_palas_end.png",
                contentVideos = listOf(
                    "C:/Users/gmasalskih/Videos/Counter-strike  Global Offensive/Counter-strike  Global Offensive 2020.10.28 - 14.06.08.01.mp4",
                    "C:/Users/gmasalskih/Videos/Counter-strike  Global Offensive/Counter-strike  Global Offensive 2020.10.28 - 15.49.58.08.mp4"
                ),
                contentImages = listOf(
                    "D:/EzCS/map_points/palas_to_under_palas_start.png",
                    "D:/EzCS/map_points/palas_to_under_palas_end.png"
                )
            )
        )
    )

    fun onNameChange(name: String) = setItemState(state.item.copy(name = name))
    fun onGrenadeTypeChange(grenadeType: GrenadeTypes) = setItemState(state.item.copy(grenadeType = grenadeType))
    fun onTickrateChange(tickrate: TickRate) {
        setItemState(
            state.item.copy(
                tickRate = if (state.item.tickRate.contains(tickrate)) state.item.tickRate.filter { it != tickrate }
                else state.item.tickRate + listOf(tickrate)
            )
        )
    }

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
}