package screens.danger_zone.edit

import androidx.compose.runtime.*
import data.entitys.DangerZone
import screens.BaseEditController
import screens.ViewState
import utils.fileChooser

class DangerZoneEditController : BaseEditController<DangerZoneEditSate>() {
    override var state: ViewState<DangerZoneEditSate> by mutableStateOf(
        ViewState(
            title = "Edit rank",
            item = DangerZoneEditSate()
        )
    )

    override suspend fun setEntity() {
        service.getEntity(documentName, DangerZone::class).let { entity ->
            state = state.copy(title = "Edit ${entity.name}")
            setItemState(
                state.item.copy(
                    logo = entity.logo,
                )
            )
        }
    }

    fun onLogoChange() {
        val newLogo = fileChooser("Select logo", "png") ?: return
        if (!state.item.logo.contains(newLogo)) setItemState(state.item.copy(logo = newLogo))
    }

    override suspend fun update(stateItem: DangerZoneEditSate) {
//        TODO("Not yet implemented")
    }

}