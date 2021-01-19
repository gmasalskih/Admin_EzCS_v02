package screens.danger_zone.menu

import androidx.compose.runtime.*
import data.entitys.DangerZone
import data.types.EntityType
import router.NavigationTargets
import screens.BaseMenuController
import screens.ViewState

class DangerZoneMenuController : BaseMenuController<DangerZoneMenuItemViewState>() {

    override val defaultItemViewState: DangerZoneMenuItemViewState = DangerZoneMenuItemViewState()

    override var viewState: ViewState<DangerZoneMenuItemViewState> by mutableStateOf(
        ViewState(
            title = "Danger Zone",
            item = defaultItemViewState
        )
    )

    fun navigateToDangerZoneAdd() {
        router.navigateTo(NavigationTargets.DangerZoneAdd)
    }

    fun navigateToDangerZoneEdit(documentName: String) {
        router.navigateTo(NavigationTargets.DangerZoneEdit(documentName))
    }

    override suspend fun setEntity() {
        setItemViewState(
            DangerZoneMenuItemViewState(
                service.getListEntitiesAsync(EntityType.DANGER_ZONE.name, DangerZone::class).await().sortedBy { it.order }
            )
        )
    }
}