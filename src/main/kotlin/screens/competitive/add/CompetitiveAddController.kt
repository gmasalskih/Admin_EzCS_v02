package screens.competitive.add

import androidx.compose.runtime.*
import data.entitys.Competitive
import screens.BaseAddController
import screens.ViewState
import utils.fileChooser
import utils.toValidName
import utils.toValidOrder

class CompetitiveAddController : BaseAddController<CompetitiveAddState>() {
    override var state: ViewState<CompetitiveAddState> by mutableStateOf(
        ViewState(
            title = "Add new competitive rank",
            item = CompetitiveAddState()
        )
    )

    override fun onClear() = setItemState(item = CompetitiveAddState())

    override fun onNameChange(name: String) = setItemState(state.item.copy(name = name.toValidName()))

    fun onOrderChange(order: String) = setItemState(state.item.copy(order = order.toValidOrder()))

    fun onLogoAdd() {
        val newLogo = fileChooser("Select logo", "png") ?: return
        if (!state.item.logo.contains(newLogo)) setItemState(state.item.copy(logo = newLogo))
    }

    override suspend fun upload(item: CompetitiveAddState) =
        service.upload(Competitive(name = item.name, logo = item.logo, order = item.order.toIntOrNull() ?: -1))
}