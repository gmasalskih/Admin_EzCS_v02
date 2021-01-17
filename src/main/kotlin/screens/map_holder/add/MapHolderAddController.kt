package screens.map_holder.add

import androidx.compose.runtime.*
import data.entitys.MapHolder
import data.types.FileType
import screens.BaseAddController
import screens.ViewState
import utils.fileChooser
import utils.toValidName

class MapHolderAddController : BaseAddController<MapHolder, MapHolderAddItemViewState>() {

    override val defaultItemViewState: MapHolderAddItemViewState = MapHolderAddItemViewState()

    override var viewState: ViewState<MapHolderAddItemViewState> by mutableStateOf(
        ViewState(
            title = "new map",
            item = defaultItemViewState
        )
    )

    fun onNameChange(name: String) {
        setItemViewState(
            viewState.item.copy(
                name = name.toValidName()
            )
        )
    }

    fun onLogoAdd() {
        fileChooser("Select logo", FileType.PNG, viewState.item.logo) { newLogo ->
            setItemViewState(
                viewState.item.copy(
                    logo = newLogo
                )
            )
        }
    }

    fun onMapAdd() {
        fileChooser("Select map", FileType.PNG, viewState.item.map) { newMap ->
            setItemViewState(
                viewState.item.copy(
                    map = newMap
                )
            )
        }
    }

    fun onWallpaperAdd() {
        fileChooser("Select wallpaper", FileType.PNG, viewState.item.wallpaper) { newWallpaper ->
            setItemViewState(
                viewState.item.copy(
                    wallpaper = newWallpaper
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

    fun onClear() {
        setDefaultState()
    }

    fun onSubmit() {
        launchUploadingEntityOnServer(viewState.item)
    }

    override fun mapper(itemViewState: MapHolderAddItemViewState): MapHolder {
        return MapHolder(
            name = itemViewState.name,
            isCompetitive = itemViewState.isCompetitive,
            logo = itemViewState.logo.value,
            map = itemViewState.map.value,
            wallpaper = itemViewState.wallpaper.value
        )
    }
}
