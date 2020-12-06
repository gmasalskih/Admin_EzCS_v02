package screens.map_holder.add

import androidx.compose.runtime.*
import kotlinx.coroutines.launch
import org.koin.core.inject
import providers.service.ServiceProvider
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
    private val serviceProvider by inject<ServiceProvider>()

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
        showLoading()
        try {
            serviceProvider.upload(state.item)
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
