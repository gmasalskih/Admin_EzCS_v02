package screens.wingman.add

import androidx.compose.runtime.*
import data.entitys.Wingman
import data.types.ContentSourceType
import screens.BaseAddController
import screens.ViewState
import utils.fileChooser
import utils.toValidName
import utils.toValidOrder

class WingmanAddController : BaseAddController<WingmanAddState>() {

    override val defaultItemState: WingmanAddState = WingmanAddState()

    override var state: ViewState<WingmanAddState> by mutableStateOf(
        ViewState(
            title = "Add new wingman rank",
            item = defaultItemState
        )
    )

    fun onNameChange(name: String) = setItemState(state.item.copy(name = name.toValidName()))

    fun onLogoAdd() {
        val newLogo = fileChooser("Select logo", "png") ?: return
        if (!state.item.logo.value.contains(newLogo))
            setItemState(state.item.copy(logo = ContentSourceType.File(newLogo)))
    }

    fun onOrderChange(order: String) = setItemState(state.item.copy(order = order.toValidOrder()))

    override suspend fun upload(stateItem: WingmanAddState) {
        service.uploadEntity(
            Wingman(
                name = stateItem.name,
                logo = stateItem.logo.value,
                order = stateItem.order
            )
        )
    }
}