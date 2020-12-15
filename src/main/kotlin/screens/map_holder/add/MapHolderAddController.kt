package screens.map_holder.add

import androidx.compose.runtime.*
import data.entitys.MapHolder
import kotlinx.coroutines.launch
import screens.BaseController
import screens.ViewState
import utils.fileChooser
import utils.toValidName

class MapHolderAddController : BaseController<MapHolderAddState>() {
    override var state: ViewState<MapHolderAddState> by mutableStateOf(
        ViewState(
            title = "Add Map",
            item = MapHolderAddState()
        )
    )

    fun onClear() {
        state = ViewState(title = "Add Map", item = MapHolderAddState())
    }

    fun onNameChange(name: String) = setItemState(state.item.copy(name = name.toValidName()))
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
    fun onSubmit() = cs.launch {
        val item = state.item
        if (!item.isValid()) throw Exception("The entity ${state.item} is not valid!")
        showLoading()
        try {
            service.upload(
                MapHolder(
                    name = item.name,
                    isCompetitive = item.isCompetitive,
                    logo = item.logo,
                    map = item.map,
                    wallpaper = item.wallpaper
                )
            )
            onClear()
        } catch (e: Exception) {
            showError(e)
        }
    }

    override fun onViewCreate() {
        super.onViewCreate()
        onClear()
    }
}
