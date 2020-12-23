package screens.map_holder.edit

import androidx.compose.runtime.*
import data.entitys.MapHolder
import data.types.ContentSourceType
import screens.BaseEditController
import screens.ViewState
import utils.fileChooser

class MapHolderEditController : BaseEditController<MapHolderEditState>() {
    override var state: ViewState<MapHolderEditState> by mutableStateOf(
        ViewState(
            title = "Edit map",
            item = MapHolderEditState()
        )
    )

    override suspend fun setEntity() {
        service.getEntity(documentName, MapHolder::class).let { entity ->
            state = state.copy(title = "Edit ${entity.name}")
            setItemState(
                MapHolderEditState(
                    name = entity.name,
                    isCompetitive = entity.isCompetitive,
                    logo = ContentSourceType.ContentStorageOriginal(entity.getDocumentName(), entity.logo),
                    map = ContentSourceType.ContentStorageThumbnail(entity.getDocumentName(), entity.map),
                    wallpaper = ContentSourceType.ContentStorageThumbnail(entity.getDocumentName(), entity.wallpaper)
                )
            )
        }
    }

    override suspend fun update(stateItem: MapHolderEditState) {
        service.updateEntity(
            MapHolder(
                name = stateItem.name,
                isCompetitive = stateItem.isCompetitive,
                logo = stateItem.logo.value,
                map = stateItem.map.value,
                wallpaper = stateItem.wallpaper.value
            )
        )
    }

    fun onCompetitiveChange(value: Boolean) = setItemState(state.item.copy(isCompetitive = value))

    fun onWallpaperChange() {
        val newWallpaper = fileChooser("Select logo", "png") ?: return
        if (!state.item.wallpaper.value.contains(newWallpaper))
            setItemState(state.item.copy(wallpaper = ContentSourceType.File(newWallpaper)))
    }

    fun onMapChange() {
        val newMap = fileChooser("Select logo", "png") ?: return
        if (!state.item.map.value.contains(newMap))
            setItemState(state.item.copy(map = ContentSourceType.File(newMap)))
    }

    fun onLogoChange() {
        val newLogo = fileChooser("Select logo", "png") ?: return
        if (!state.item.logo.value.contains(newLogo))
            setItemState(state.item.copy(logo = ContentSourceType.File(newLogo)))
    }


}
