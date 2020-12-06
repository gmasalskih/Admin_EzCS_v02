package screens.danger_zone.edit

import androidx.compose.runtime.*
import data.entitys.DangerZone
import screens.BaseController
import screens.ViewState
import utils.fileChooser

class DangerZoneEditController : BaseController<DangerZoneEditSate>() {
    override var state: ViewState<DangerZoneEditSate> by mutableStateOf(ViewState(title = "Edit rank", item = DangerZoneEditSate()))

    fun onNameChange(name: String) = setItemState(state.item.copy(name = name))

    fun onLogoChange() {
        val newLogo = fileChooser("Select logo", "png") ?: return
        if (!state.item.logo.contains(newLogo)) setItemState(state.item.copy(logo = newLogo))
    }

    fun onSubmit() {
        //TODO implement fun onSubmit
    }
}