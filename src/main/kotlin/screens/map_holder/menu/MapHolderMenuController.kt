package screens.map_holder.menu

import androidx.compose.runtime.*
import data.types.EntityType
import data.entitys.MapHolder
import kotlinx.coroutines.*
import router.NavigationTargets
import screens.BaseController
import screens.ViewState

class MapHolderMenuController : BaseController<List<MapHolder>>() {
    override var state: ViewState<List<MapHolder>> by mutableStateOf(ViewState(title = "Maps", item = listOf()))

    fun navigateToMapHolderAdd() {
        router.navigateTo(NavigationTargets.MapHolderAdd)
    }

    fun navigateToMapHolderEdit(documentName: String) {
        router.navigateTo(NavigationTargets.MapHolderEdit(documentName))
    }

    private fun initState() = cs.launch {



        try {
            val maps = service.retrieveEntities(EntityType.MAP_HOLDER.name, MapHolder::class)
            setItemState(maps)
            showData()
        } catch (e: Exception) {
            showError(e)
        }
    }

    override fun onViewCreate() {
        super.onViewCreate()
        showLoading()
        initState()
    }

    override fun onViewDestroy() {
        super.onViewDestroy()
        setItemState(listOf())
    }
}