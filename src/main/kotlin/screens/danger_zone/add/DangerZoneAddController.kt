package screens.danger_zone.add

import androidx.compose.runtime.*
import data.entitys.DangerZone
import screens.BaseController
import screens.ViewState
import utils.fileChooser

class DangerZoneAddController : BaseController<DangerZoneAddState>() {
    override var state: ViewState<DangerZoneAddState> by mutableStateOf(
        ViewState(
            title = "Add new competitive rank",
            item = DangerZoneAddState()
        )
    )

    fun onClear() {
        state = ViewState(title = "Add new competitive rank", item = DangerZoneAddState())
    }

    fun onLogoAdd() {
        val newLogo = fileChooser("Select logo", "png") ?: return
        if (!state.item.logo.contains(newLogo)) setItemState(state.item.copy(logo = newLogo))
    }

    fun onNameChange(name: String) = setItemState(state.item.copy(name = name))
    fun onSubmit() {
//        TODO("Not yet implemented")
    }
}