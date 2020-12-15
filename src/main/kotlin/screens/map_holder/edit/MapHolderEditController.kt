package screens.map_holder.edit

import androidx.compose.runtime.*
import data.entitys.MapHolder
import kotlinx.coroutines.launch
import screens.BaseController
import screens.ViewState
import utils.fileChooser

class MapHolderEditController : BaseController<MapHolderEditState>() {
    override var state: ViewState<MapHolderEditState> by mutableStateOf(
        ViewState(
            title = "Edit map",
            item = MapHolderEditState()
        )
    )
    private lateinit var documentName: String

    private fun initState() = cs.launch {
        val item = service.retrieveEntity(documentName, MapHolder::class)
        setItemState(
            state.item.copy(
                name = item.name,
                logo = item.logo,
                map = item.map,
                wallpaper = item.wallpaper,
                isCompetitive = item.isCompetitive
            )
        )
        showData()
    }

    fun onCompetitiveChange(value: Boolean) = setItemState(state.item.copy(isCompetitive = value))
    fun onSubmit() {
//        TODO("Not yet implemented")
    }

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

    fun onNameChange(name: String) = setItemState(state.item.copy(name = name))

    fun onDelete() = cs.launch {
        showLoading()
        service.delete(documentName)
        showData()
        router.back()
    }

    fun setDocumentName(documentName: String) {
        this.documentName = documentName
    }

    override fun onViewCreate() {
        super.onViewCreate()
        showLoading()
        initState()
    }

    override fun onViewDestroy() {
        super.onViewDestroy()
    }
}