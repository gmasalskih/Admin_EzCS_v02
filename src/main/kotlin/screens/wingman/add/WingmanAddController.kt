package screens.wingman.add

import androidx.compose.runtime.*
import data.entitys.Wingman
import screens.BaseAddController
import screens.ViewState
import utils.fileChooser
import utils.toValidOrder

class WingmanAddController : BaseAddController<WingmanAddState>() {

    override var state: ViewState<WingmanAddState> by mutableStateOf(
        ViewState(
            title = "Add new wingman rank",
            item = WingmanAddState()
        )
    )

    override fun onNameChange(name: String) = setItemState(state.item.copy(name = name))

    fun onLogoAdd() {
        val newLogo = fileChooser("Select logo", "png") ?: return
        if (!state.item.logo.contains(newLogo)) setItemState(state.item.copy(logo = newLogo))
    }

    fun onOrderChange(order: String) = setItemState(state.item.copy(order = order.toValidOrder()))

    override fun onClear() {
        state = ViewState(title = "Add new wingman rank", item = WingmanAddState())
    }

    override suspend fun upload(item: WingmanAddState) =
        service.uploadEntity(Wingman(name = item.name, logo = item.logo, order = item.order.toIntOrNull() ?: -1))

}