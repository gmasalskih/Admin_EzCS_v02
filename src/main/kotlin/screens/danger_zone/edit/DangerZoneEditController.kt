package screens.danger_zone.edit

import androidx.compose.runtime.*
import data.pojo.DangerZone
import screens.BaseController
import screens.ViewState
import utils.fileChooser

class DangerZoneEditController : BaseController<DangerZone>() {
    override var state: ViewState<DangerZone> by mutableStateOf(ViewState(title = "Edit rank", item = DangerZone()))

    fun setId(id: String) = setItemState(state.item.copy(id = id))

    fun onNameChange(name: String) = setItemState(state.item.copy(name = name))

    fun onLogoChange() {
        val newLogo = fileChooser("Select logo", "png") ?: return
        if (!state.item.logo.contains(newLogo)) setItemState(state.item.copy(logo = newLogo))
    }

    fun onSubmit() {
//        TODO("Not yet implemented")
    }
}