package screens.map_holder.edit

import androidx.compose.runtime.*
import data.entitys.MapHolder
import data.types.ContentSourceType
import data.types.FileType
import screens.BaseEditController
import screens.ViewState
import utils.fileChooser

class MapHolderEditController : BaseEditController<MapHolderEditState>() {

    override val defaultItemState: MapHolderEditState = MapHolderEditState()

    override var state: ViewState<MapHolderEditState> by mutableStateOf(
        ViewState(
            title = "Edit map",
            item = defaultItemState
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

    fun onCompetitiveChange(value: Boolean) {
        setItemState(
            state.item.copy(
                isCompetitive = value
            )
        )
    }

    fun onWallpaperChange() {
        fileChooser("Select logo", FileType.PNG, state.item.wallpaper) { newWallpaper ->
            setItemState(
                state.item.copy(
                    wallpaper = newWallpaper
                )
            )
        }
    }

    fun onMapChange() {
        fileChooser("Select logo", FileType.PNG, state.item.map) { newMap ->
            setItemState(
                state.item.copy(
                    map = newMap
                )
            )
        }
    }

    fun onLogoChange() {
        fileChooser("Select logo", FileType.PNG, state.item.logo) { newLogo ->
            setItemState(
                state.item.copy(
                    logo = newLogo
                )
            )
        }
    }

    fun onDelete(){
        launchDeletingEntityOnServer()
    }

    fun onSubmit() {
        launchUpdatingEntityOnServer(state.item)
    }
}
