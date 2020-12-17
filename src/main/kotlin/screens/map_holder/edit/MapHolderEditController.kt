package screens.map_holder.edit

import androidx.compose.runtime.*
import data.entitys.MapHolder
import kotlinx.coroutines.launch
import screens.BaseController
import screens.ViewState
import utils.fileChooser
import utils.isValidPathToFile

class MapHolderEditController : BaseController<MapHolderEditState>() {
    override var state: ViewState<MapHolderEditState> by mutableStateOf(
        ViewState(
            title = "Edit map",
            item = MapHolderEditState()
        )
    )
    private lateinit var documentName: String
    private lateinit var entity: MapHolder

    private fun initState() = cs.launch {
        entity = service.retrieveRawEntity(documentName, MapHolder::class)
        service.retrieveEntity(documentName, MapHolder::class).let { entity ->
            setItemState(
                state.item.copy(
                    name = entity.name,
                    logo = entity.logo,
                    map = entity.map,
                    wallpaper = entity.wallpaper,
                    isCompetitive = entity.isCompetitive
                )
            )
        }
        showData()
        println(entity)
    }

    fun onCompetitiveChange(value: Boolean) = setItemState(state.item.copy(isCompetitive = value))

    fun onSubmit() = cs.launch {
        showLoading()
        try {
            service.update(
                entity.copy(
                    name = state.item.name,
                    logo = if (state.item.logo.isValidPathToFile()) state.item.logo else entity.logo,
                    map = if (state.item.map.isValidPathToFile()) state.item.map else entity.map,
                    wallpaper = if (state.item.wallpaper.isValidPathToFile()) state.item.wallpaper else entity.wallpaper,
                    isCompetitive = state.item.isCompetitive
                )
            )
            showData()
            router.back()
        } catch (e: Exception) {
            e.printStackTrace()
            showError(e)
        }
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