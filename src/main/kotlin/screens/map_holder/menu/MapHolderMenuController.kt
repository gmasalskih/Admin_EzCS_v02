package screens.map_holder.menu

import androidx.compose.runtime.*
import data.types.EntityType
import data.entitys.MapHolder
import kotlinx.coroutines.*
import router.NavigationTargets
import screens.BaseController
import screens.BaseMenuController
import screens.ViewState

class MapHolderMenuController : BaseMenuController<List<MapHolder>>() {
    override var state: ViewState<List<MapHolder>> by mutableStateOf(ViewState(title = "Maps", item = listOf()))

    fun navigateToMapHolderAdd() {
        router.navigateTo(NavigationTargets.MapHolderAdd)
    }

    fun navigateToMapHolderEdit(documentName: String) {
        router.navigateTo(NavigationTargets.MapHolderEdit(documentName))
    }

    override fun initState() {
        setItemState(listOf())
        cs.launch {
            try {
                setItemState(service.retrieveEntities(EntityType.MAP_HOLDER.name, MapHolder::class))
                showData()
            } catch (e: Exception) {
                showError(e)
            }
        }
    }
}