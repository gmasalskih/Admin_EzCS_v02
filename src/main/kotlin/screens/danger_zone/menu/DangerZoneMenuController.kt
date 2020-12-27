package screens.danger_zone.menu

import androidx.compose.runtime.*
import data.entitys.DangerZone
import data.types.EntityType
import router.NavigationTargets
import screens.BaseMenuController
import screens.ViewState

class DangerZoneMenuController : BaseMenuController<DangerZoneMenuState>() {

    override val defaultItemState: DangerZoneMenuState = DangerZoneMenuState()

    override var state: ViewState<DangerZoneMenuState> by mutableStateOf(
        ViewState(
            title = "Danger Zone",
            item = defaultItemState
        )
    )

    fun navigateToDangerZoneAdd() {
        router.navigateTo(NavigationTargets.DangerZoneAdd)
    }

    fun navigateToDangerZoneEdit(documentName: String) {
        router.navigateTo(NavigationTargets.DangerZoneEdit(documentName))
    }

    override suspend fun setEntity() {
        setItemState(
            DangerZoneMenuState(
                service.getListEntities(EntityType.DANGER_ZONE.name, DangerZone::class).sortedBy { it.order }
            )
        )
    }
}