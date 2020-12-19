package screens.wingman.add

import androidx.compose.runtime.*
import data.entitys.Wingman
import screens.BaseController
import screens.ViewState
import utils.fileChooser

class WingmanAddController : BaseController<WingmanAddState>() {

    override var state: ViewState<WingmanAddState> by mutableStateOf(ViewState(title = "Add new wingman rank", item = WingmanAddState()))

    fun onNameChange(name: String) = setItemState(state.item.copy(name = name))

    fun onLogoAdd() {
        val newLogo = fileChooser("Select logo", "png") ?: return
        if (!state.item.logo.contains(newLogo)) setItemState(state.item.copy(logo = newLogo))
    }

    fun onClear() {
        state = ViewState(title = "Add new wingman rank", item = WingmanAddState())
    }

    fun onSubmit() {
//        TODO("Not yet implemented")
    }

    override fun initState() {
//        TODO("Not yet implemented")
    }
}