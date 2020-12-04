package screens.map_holder.add

import androidx.compose.runtime.*
import data.pojo.MapHolder
import screens.BaseController
import screens.ViewState
import utils.fileChooser

class MapHolderAddController : BaseController<MapHolder>() {
    override var state: ViewState<MapHolder> by mutableStateOf(ViewState(title = "Add Map", item = MapHolder()))

    fun onClear() {
        state = ViewState(title = "Add Map", item = MapHolder())
    }

    fun onNameChange(name: String) = setItemState(state.item.copy(name = name))
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
    fun onSubmit() {
//        TODO("Not yet implemented")
    }

    override fun onViewCreate() {
        super.onViewCreate()
        onClear()
    }
}