package screens.danger_zone.menu

import androidx.compose.runtime.*
import data.entitys.DangerZone
import data.types.EntityType
import router.NavigationTargets
import screens.BaseMenuController
import screens.ViewState

class DangerZoneMenuController : BaseMenuController<List<DangerZone>>() {
    override var state: ViewState<List<DangerZone>> by mutableStateOf(
        ViewState(
            title = "Danger Zone",
            item = listOf()
        )
    )

    fun navigateToDangerZoneAdd() {
        router.navigateTo(NavigationTargets.DangerZoneAdd)
    }

    fun navigateToDangerZoneEdit(documentName: String) {
        router.navigateTo(NavigationTargets.DangerZoneEdit(documentName))
    }

    override fun onClear() {
        setItemState(listOf())
    }

    override suspend fun setEntities() {
        setItemState(service.retrieveEntities(EntityType.DANGER_ZONE.name, DangerZone::class).sortedBy { it.order })
    }

}