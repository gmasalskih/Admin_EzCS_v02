package screens.map_holder.menu

import androidx.compose.runtime.*
import data.types.EntityType
import data.entitys.MapHolder
import router.NavigationTargets
import screens.BaseMenuController
import screens.ViewState

class MapHolderMenuController : BaseMenuController<MapHolderMenuState>() {

    override val defaultItemState: MapHolderMenuState = MapHolderMenuState()

    override var state: ViewState<MapHolderMenuState> by mutableStateOf(
        ViewState(
            title = "Map holder",
            item = defaultItemState
        )
    )

    fun navigateToMapHolderAdd() {
        router.navigateTo(NavigationTargets.MapHolderAdd)
    }

    fun navigateToMapHolderEdit(documentName: String) {
        router.navigateTo(NavigationTargets.MapHolderEdit(documentName))
    }

    override suspend fun setEntity() {
        setItemState(
            MapHolderMenuState(
                service.getListEntities(EntityType.MAP_HOLDER.name, MapHolder::class)
            )
        )
    }
}