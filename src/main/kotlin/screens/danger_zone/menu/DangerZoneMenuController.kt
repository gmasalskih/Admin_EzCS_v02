package screens.danger_zone.menu

import androidx.compose.runtime.*
import data.entitys.DangerZone
import data.types.ContentSourceType
import data.types.EntityType
import router.NavigationTargets
import screens.BaseMenuController
import screens.ViewState

class DangerZoneMenuController : BaseMenuController<List<DangerZoneMenuState>>() {
    override var state: ViewState<List<DangerZoneMenuState>> by mutableStateOf(
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
        setItemState(
            service.getListEntities(EntityType.DANGER_ZONE.name, DangerZone::class).map { entity ->
                DangerZoneMenuState(
                    name = entity.name,
                    documentName = entity.getDocumentName(),
                    logo = ContentSourceType.ContentStorageOriginal(entity.getDocumentName(), entity.logo),
                    order = entity.order
                )
            }.sortedBy { it.order }
        )
    }
}