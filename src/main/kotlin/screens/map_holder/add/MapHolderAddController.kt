package screens.map_holder.add

import androidx.compose.runtime.*
import data.entitys.MapHolder
import data.types.ContentSourceType
import screens.BaseAddController
import screens.ViewState
import utils.fileChooser
import utils.toValidName

class MapHolderAddController : BaseAddController<MapHolderAddState>() {
    override var state: ViewState<MapHolderAddState> by mutableStateOf(
        ViewState(
            title = "Add Map",
            item = MapHolderAddState()
        )
    )

    override fun onClear() = setItemState(MapHolderAddState())

    override suspend fun upload(stateItem: MapHolderAddState) =
        service.uploadEntity(
            MapHolder(
                name = stateItem.name,
                isCompetitive = stateItem.isCompetitive,
                logo = stateItem.logo.value,
                map = stateItem.map.value,
                wallpaper = stateItem.wallpaper.value
            )
        )

    override fun onNameChange(name: String) = setItemState(state.item.copy(name = name.toValidName()))

    fun onLogoAdd() {
        val newLogo = fileChooser("Select logo", "png") ?: return
        if (!state.item.logo.value.contains(newLogo))
            setItemState(state.item.copy(logo = ContentSourceType.File(newLogo)))
    }

    fun onMapAdd() {
        val newMap = fileChooser("Select map", "png") ?: return
        if (!state.item.map.value.contains(newMap))
            setItemState(state.item.copy(map = ContentSourceType.File(newMap)))
    }

    fun onWallpaperAdd() {
        val newWallpaper = fileChooser("Select wallpaper", "png") ?: return
        if (!state.item.wallpaper.value.contains(newWallpaper))
            setItemState(state.item.copy(wallpaper = ContentSourceType.File(newWallpaper)))
    }

    fun onCompetitiveChange(value: Boolean) = setItemState(state.item.copy(isCompetitive = value))
}
