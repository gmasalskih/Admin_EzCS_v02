package screens.competitive.edit

import androidx.compose.runtime.*
import screens.BaseController
import screens.ViewState
import utils.fileChooser

class CompetitiveEditController : BaseController<CompetitiveEditState>() {
    override var state: ViewState<CompetitiveEditState> by mutableStateOf(ViewState(title = "Edit rank", item = CompetitiveEditState()))

    fun onNameChange(name: String) = setItemState(state.item.copy(name = name))

    fun onLogoChange() {
        val newLogo = fileChooser("Select logo", "png") ?: return
        if (!state.item.logo.contains(newLogo)) setItemState(state.item.copy(logo = newLogo))
    }

    fun onSubmit() {
        //TODO implement fun onSubmit
    }
}