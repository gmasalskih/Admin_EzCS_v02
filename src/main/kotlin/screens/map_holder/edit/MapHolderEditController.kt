package screens.map_holder.edit

import androidx.compose.runtime.*
import data.entitys.MapHolder
import data.types.ContentSourceType
import data.types.FileType
import screens.BaseEditController
import screens.ViewState
import utils.fileChooser

class MapHolderEditController : BaseEditController<MapHolder, MapHolderEditItemViewState>() {

    override val defaultItemViewState: MapHolderEditItemViewState = MapHolderEditItemViewState()

    override var viewState: ViewState<MapHolderEditItemViewState> by mutableStateOf(
        ViewState(
            title = "Edit map",
            item = defaultItemViewState
        )
    )

    override suspend fun setEntity() {
        service.getEntityAsync(documentName, MapHolder::class).await().let { entity ->
            viewState = viewState.copy(title = "Edit ${entity.name}")
            setItemViewState(
                MapHolderEditItemViewState(
                    name = entity.name,
                    isCompetitive = entity.isCompetitive,
                    logo = ContentSourceType.ContentStorageOriginal(entity.getDocumentName(), entity.logo),
                    map = ContentSourceType.ContentStorageThumbnail(entity.getDocumentName(), entity.map),
                    wallpaper = ContentSourceType.ContentStorageThumbnail(entity.getDocumentName(), entity.wallpaper)
                )
            )
        }
    }

    fun onCompetitiveChange(value: Boolean) {
        setItemViewState(
            viewState.item.copy(
                isCompetitive = value
            )
        )
    }

    fun onWallpaperChange() {
        fileChooser("Select logo", FileType.PNG, viewState.item.wallpaper) { newWallpaper ->
            setItemViewState(
                viewState.item.copy(
                    wallpaper = newWallpaper
                )
            )
        }
    }

    fun onMapChange() {
        fileChooser("Select logo", FileType.PNG, viewState.item.map) { newMap ->
            setItemViewState(
                viewState.item.copy(
                    map = newMap
                )
            )
        }
    }

    fun onLogoChange() {
        fileChooser("Select logo", FileType.PNG, viewState.item.logo) { newLogo ->
            setItemViewState(
                viewState.item.copy(
                    logo = newLogo
                )
            )
        }
    }

    fun onDelete() {
        launchDeletingEntityOnServer()
    }

    fun onSubmit() {
        launchUpdatingEntityOnServer(viewState.item)
    }

    override fun mapper(itemViewState: MapHolderEditItemViewState): MapHolder {
        return MapHolder(
            name = itemViewState.name,
            isCompetitive = itemViewState.isCompetitive,
            logo = itemViewState.logo.value,
            map = itemViewState.map.value,
            wallpaper = itemViewState.wallpaper.value
        )
    }
}
