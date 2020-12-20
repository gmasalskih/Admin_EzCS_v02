package screens.danger_zone.edit

import androidx.compose.runtime.*
import data.entitys.DangerZone
import screens.BaseEditController
import screens.ViewState
import utils.fileChooser
import utils.isValidPathToFile

class DangerZoneEditController : BaseEditController<DangerZone, DangerZoneEditSate>() {
    override var state: ViewState<DangerZoneEditSate> by mutableStateOf(
        ViewState(
            title = "Edit rank",
            item = DangerZoneEditSate()
        )
    )

    override suspend fun setRowEntity() {
        entity = service.retrieveRawEntity(documentName, DangerZone::class)
    }

    override suspend fun setEntity() {
        service.retrieveEntity(documentName, DangerZone::class).let { entity ->
            state = state.copy(title = "Edit ${entity.name}")
            setItemState(
                state.item.copy(
                    logo = entity.logo,
                )
            )
        }
    }

    override suspend fun update() {
        service.update(
            entity.copy(
                logo = if (state.item.logo.isValidPathToFile()) state.item.logo else entity.logo
            )
        )
    }

    fun onLogoChange() {
        val newLogo = fileChooser("Select logo", "png") ?: return
        if (!state.item.logo.contains(newLogo)) setItemState(state.item.copy(logo = newLogo))
    }

}