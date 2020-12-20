package screens.map_holder.menu

import androidx.compose.runtime.*
import data.types.EntityType
import data.entitys.MapHolder
import router.NavigationTargets
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

    override fun onClear() {
        setItemState(listOf())
    }

    override suspend fun setEntities() {
        setItemState(service.retrieveEntities(EntityType.MAP_HOLDER.name, MapHolder::class))
    }
}