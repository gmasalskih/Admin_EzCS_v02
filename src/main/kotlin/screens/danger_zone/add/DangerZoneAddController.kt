package screens.danger_zone.add

import androidx.compose.runtime.*
import data.pojo.DangerZone
import screens.BaseController
import screens.ViewState
import utils.fileChooser

class DangerZoneAddController : BaseController<DangerZone>() {
    override var state: ViewState<DangerZone> by mutableStateOf(
        ViewState(
            title = "Add new competitive rank",
            item = DangerZone()
        )
    )

    fun onClear() {
        state = ViewState(title = "Add new competitive rank", item = DangerZone())
    }

    fun onLogoAdd() {
        val newLogo = fileChooser("Select logo", "png") ?: return
        if (!state.item.logo.contains(newLogo)) setItemState(state.item.copy(logo = newLogo))
    }

    fun onNameChange(name: String) = setItemState(state.item.copy(name = name))
    fun onIdChange(id: String) = setItemState(state.item.copy(id = id))
    fun onSubmit() {
//        TODO("Not yet implemented")
    }
}