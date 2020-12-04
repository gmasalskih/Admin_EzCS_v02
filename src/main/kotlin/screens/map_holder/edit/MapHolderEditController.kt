package screens.map_holder.edit

import androidx.compose.runtime.*
import data.entitys.MapHolder
import screens.BaseController
import screens.ViewState
import utils.fileChooser

class MapHolderEditController : BaseController<MapHolder>() {
    override var state: ViewState<MapHolder> by mutableStateOf(ViewState(title = "Edit map", item = MapHolder()))

    fun onCompetitiveChange(value: Boolean) = setItemState(state.item.copy(isCompetitive = value))
    fun onSubmit() {
//        TODO("Not yet implemented")
    }

    fun onWallpaperChange() {
        val newWallpaper = fileChooser("Select logo", "png") ?: return
        if (!state.item.wallpaper.contains(newWallpaper)) setItemState(state.item.copy(wallpaper = newWallpaper))
    }

    fun onMapChange() {
        val newMap = fileChooser("Select logo", "png") ?: return
        if (!state.item.map.contains(newMap)) setItemState(state.item.copy(map = newMap))
    }

    fun onLogoChange() {
        val newLogo = fileChooser("Select logo", "png") ?: return
        if (!state.item.logo.contains(newLogo)) setItemState(state.item.copy(logo = newLogo))
    }

    fun onNameChange(name: String) = setItemState(state.item.copy(name = name))

}