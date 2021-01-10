package screens.map_holder.add

import androidx.compose.runtime.*
import data.entitys.MapHolder
import data.types.FileType
import kotlinx.coroutines.launch
import screens.BaseAddController
import screens.ViewState
import utils.fileChooser
import utils.toValidName

class MapHolderAddController : BaseAddController<MapHolderAddState>() {

    override val defaultItemState: MapHolderAddState = MapHolderAddState()

    override var state: ViewState<MapHolderAddState> by mutableStateOf(
        ViewState(
            title = "new map",
            item = defaultItemState
        )
    )

    override suspend fun upload(stateItem: MapHolderAddState) {
        service.uploadEntity(
            MapHolder(
                name = stateItem.name,
                isCompetitive = stateItem.isCompetitive,
                logo = stateItem.logo.value,
                map = stateItem.map.value,
                wallpaper = stateItem.wallpaper.value
            )
        )
    }

    fun onNameChange(name: String) {
        setItemState(
            state.item.copy(
                name = name.toValidName()
            )
        )
    }

    fun onLogoAdd() {
        fileChooser("Select logo", FileType.PNG, state.item.logo) { newLogo ->
            setItemState(
                state.item.copy(
                    logo = newLogo
                )
            )
        }
    }

    fun onMapAdd() {
        fileChooser("Select map", FileType.PNG, state.item.map) { newMap ->
            setItemState(
                state.item.copy(
                    map = newMap
                )
            )
        }
    }

    fun onWallpaperAdd() {
        fileChooser("Select wallpaper", FileType.PNG, state.item.wallpaper) { newWallpaper ->
            setItemState(
                state.item.copy(
                    wallpaper = newWallpaper
                )
            )
        }
    }

    fun onCompetitiveChange(value: Boolean) {
        setItemState(
            state.item.copy(
                isCompetitive = value
            )
        )
    }

    fun onClear(){
        setDefaultState()
    }

    fun onSubmit() {
        launchUploadingEntityOnServer(state.item)
    }
}
