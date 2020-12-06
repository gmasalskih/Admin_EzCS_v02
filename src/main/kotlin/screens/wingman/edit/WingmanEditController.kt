package screens.wingman.edit

import androidx.compose.runtime.*
import data.entitys.Wingman
import screens.BaseController
import screens.ViewState
import utils.fileChooser

class WingmanEditController : BaseController<WingmanEditState>() {

    override var state: ViewState<WingmanEditState> by mutableStateOf(ViewState(title = "Edit rank", item = WingmanEditState()))

    fun onNameChange(name: String) = setItemState(state.item.copy(name = name))

    fun onLogoChange() {
        val newLogo = fileChooser("Select logo", "png") ?: return
        if (!state.item.logo.contains(newLogo)) setItemState(state.item.copy(logo = newLogo))
    }

    fun onSubmit() {
        //TODO implement fun onSubmit
    }
}