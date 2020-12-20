package screens.map_holder.edit

import androidx.compose.runtime.*
import data.entitys.MapHolder
import screens.BaseEditController
import screens.ViewState
import utils.fileChooser
import utils.isValidPathToFile

class MapHolderEditController : BaseEditController<MapHolder, MapHolderEditState>() {
    override var state: ViewState<MapHolderEditState> by mutableStateOf(
        ViewState(
            title = "Edit map",
            item = MapHolderEditState()
        )
    )

    override suspend fun setRowEntity() {
        entity = service.retrieveRawEntity(documentName, MapHolder::class)
    }

    override suspend fun setEntity() {
        service.retrieveEntity(documentName, MapHolder::class).let { entity ->
            state = state.copy(title = "Edit ${entity.name}")
            setItemState(
                state.item.copy(
                    logo = entity.logo,
                    map = entity.map,
                    wallpaper = entity.wallpaper,
                    isCompetitive = entity.isCompetitive
                )
            )
        }
    }

    override suspend fun update() {
        service.update(
            entity.copy(
                logo = if (state.item.logo.isValidPathToFile()) state.item.logo else entity.logo,
                map = if (state.item.map.isValidPathToFile()) state.item.map else entity.map,
                wallpaper = if (state.item.wallpaper.isValidPathToFile()) state.item.wallpaper else entity.wallpaper,
                isCompetitive = state.item.isCompetitive
            )
        )
    }

    fun onCompetitiveChange(value: Boolean) = setItemState(state.item.copy(isCompetitive = value))

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
}