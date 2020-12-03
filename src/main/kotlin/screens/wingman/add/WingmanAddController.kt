package screens.wingman.add

import androidx.compose.runtime.*
import data.pojo.Wingman
import screens.BaseController
import screens.ViewState
import utils.fileChooser

class WingmanAddController : BaseController<Wingman>() {

    override var state: ViewState<Wingman> by mutableStateOf(ViewState(title = "Add new wingman rank", item = Wingman()))

    fun onNameChange(name: String) = setItemState(state.item.copy(name = name))

    fun onLogoAdd() {
        val newLogo = fileChooser("Select logo", "png") ?: return
        if (!state.item.logo.contains(newLogo)) setItemState(state.item.copy(logo = newLogo))
    }

    fun onClear() {
        state = ViewState(title = "Add new wingman rank", item = Wingman())
    }

    fun onSubmit() {
//        TODO("Not yet implemented")
    }
}