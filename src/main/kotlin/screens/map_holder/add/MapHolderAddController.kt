package screens.map_holder.add

import androidx.compose.runtime.*
import data.entitys.MapHolder
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

    override suspend fun upload(item: MapHolderAddState) =
        service.upload(
            MapHolder(
                name = item.name,
                isCompetitive = item.isCompetitive,
                logo = item.logo,
                map = item.map,
                wallpaper = item.wallpaper
            )
        )

    override fun onNameChange(name: String) = setItemState(state.item.copy(name = name.toValidName()))

    fun onLogoAdd() {
        val newLogo = fileChooser("Select logo", "png") ?: return
        if (!state.item.logo.contains(newLogo)) setItemState(state.item.copy(logo = newLogo))
    }

    fun onMapAdd() {
        val newMap = fileChooser("Select map", "png") ?: return
        if (!state.item.map.contains(newMap)) setItemState(state.item.copy(map = newMap))
    }

    fun onWallpaperAdd() {
        val newWallpaper = fileChooser("Select wallpaper", "png") ?: return
        if (!state.item.wallpaper.contains(newWallpaper)) setItemState(state.item.copy(wallpaper = newWallpaper))
    }

    fun onCompetitiveChange(value: Boolean) = setItemState(state.item.copy(isCompetitive = value))
}
