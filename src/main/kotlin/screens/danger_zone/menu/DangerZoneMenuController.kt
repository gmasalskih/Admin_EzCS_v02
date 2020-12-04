package screens.danger_zone.menu

import androidx.compose.runtime.*
import data.entitys.DangerZone
import router.NavigationTargets
import screens.BaseController
import screens.ViewState

class DangerZoneMenuController : BaseController<List<DangerZone>>() {
    override var state: ViewState<List<DangerZone>> by mutableStateOf(
        ViewState(
            title = "Danger Zone",
            item = listOf(
                DangerZone(
                    name = "Lab rat i",
                    logo = ""
                ),
                DangerZone(
                    name = "lab rat ii",
                    logo = ""
                ),
                DangerZone(
                    name = "sprinting hare i",
                    logo = ""
                ),
            )
        )
    )

    fun navigateToDangerZoneAdd() {
        router.navigateTo(NavigationTargets.DangerZoneAdd)
    }

    fun navigateToDangerZoneEdit(id: String) {
        router.navigateTo(NavigationTargets.DangerZoneEdit(id))
    }
}