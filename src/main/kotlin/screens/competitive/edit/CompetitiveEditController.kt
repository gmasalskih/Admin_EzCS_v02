package screens.competitive.edit

import androidx.compose.runtime.*
import data.pojo.Competitive
import screens.BaseController
import screens.ViewState
import utils.fileChooser

class CompetitiveEditController : BaseController<Competitive>() {
    override var state: ViewState<Competitive> by mutableStateOf(ViewState(title = "Edit rank", item = Competitive()))

    fun onNameChange(name: String) = setItemState(state.item.copy(name = name))

    fun onLogoChange() {
        val newLogo = fileChooser("Select logo", "png") ?: return
        if (!state.item.logo.contains(newLogo)) setItemState(state.item.copy(logo = newLogo))
    }

    fun onSubmit() {
//        TODO("Not yet implemented")
    }
}