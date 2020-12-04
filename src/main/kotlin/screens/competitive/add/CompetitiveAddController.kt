package screens.competitive.add

import androidx.compose.runtime.*
import screens.BaseController
import screens.ViewState
import utils.fileChooser

class CompetitiveAddController : BaseController<CompetitiveAddState>() {
    override var state: ViewState<CompetitiveAddState> by mutableStateOf(
        ViewState(
            title = "Add new competitive rank",
            item = CompetitiveAddState()
        )
    )

    fun onClear() {
        state = ViewState(title = "Add new competitive rank", item = CompetitiveAddState())
    }



    fun onNameChange(name: String) = setItemState(state.item.copy(name = name))

    fun onLogoAdd() {
        val newLogo = fileChooser("Select logo", "png") ?: return
        if (!state.item.logo.contains(newLogo)) setItemState(state.item.copy(logo = newLogo))
    }

    fun onSubmit() {
//        TODO("Not yet implemented")
    }
}