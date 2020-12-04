package screens.map_holder.add

import androidx.compose.runtime.*
import data.entitys.MapHolder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.inject
import providers.dropbox.DropboxProvider
import providers.firebase.FirestoreProvider
import screens.BaseController
import screens.ViewState
import utils.fileChooser

class MapHolderAddController : BaseController<MapHolderAddState>() {
    override var state: ViewState<MapHolderAddState> by mutableStateOf(
        ViewState(
            title = "Add Map",
            item = MapHolderAddState()
        )
    )
    private val firestoreProvider by inject<FirestoreProvider>()
    private val dropboxProvider by inject<DropboxProvider>()

    fun onClear() {
        state = ViewState(title = "Add Map", item = MapHolderAddState())
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
    fun onSubmit() = cs.launch {
        showLoading()
        val entity = stateToEntity(state.item)
        if (!state.item.isValid()) {
            showError(Exception("The map holder have empty fields"))
            return@launch
        }
        try {
            dropboxProvider.uploadFile(state.item.logo, entity.createContentsPath())
            dropboxProvider.uploadFile(state.item.map, entity.createContentsPath())
            dropboxProvider.uploadFile(state.item.wallpaper, entity.createContentsPath())
            firestoreProvider.uploadEntity(entity)
            router.back()
        } catch (e: Exception) {
            showError(e)
        }
    }

    private suspend fun stateToEntity(viewState: MapHolderAddState) = withContext(Dispatchers.IO) {
        MapHolder(
            name = viewState.name,
            isCompetitive = viewState.isCompetitive,
            logo = viewState.logo.split("/").last(),
            map = viewState.map.split("/").last(),
            wallpaper = viewState.wallpaper.split("/").last()
        )
    }

    override fun onViewCreate() {
        super.onViewCreate()
        onClear()
    }
}