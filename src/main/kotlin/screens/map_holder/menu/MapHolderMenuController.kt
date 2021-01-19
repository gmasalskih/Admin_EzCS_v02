package screens.map_holder.menu

import androidx.compose.runtime.*
import data.types.EntityType
import data.entitys.MapHolder
import router.NavigationTargets
import screens.BaseMenuController
import screens.ViewState

class MapHolderMenuController : BaseMenuController<MapHolderMenuItemViewState>() {

    override val defaultItemViewState: MapHolderMenuItemViewState = MapHolderMenuItemViewState()

    override var viewState: ViewState<MapHolderMenuItemViewState> by mutableStateOf(
        ViewState(
            title = "Map holder",
            item = defaultItemViewState
        )
    )

    fun navigateToMapHolderAdd() {
        router.navigateTo(NavigationTargets.MapHolderAdd)
    }

    fun navigateToMapHolderEdit(documentName: String) {
        router.navigateTo(NavigationTargets.MapHolderEdit(documentName))
    }

    override suspend fun setEntity() {
        setItemViewState(
            MapHolderMenuItemViewState(
                service.getListEntitiesAsync(EntityType.MAP_HOLDER.name, MapHolder::class).await()
            )
        )
    }
}