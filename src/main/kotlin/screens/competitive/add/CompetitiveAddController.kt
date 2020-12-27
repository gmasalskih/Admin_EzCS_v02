package screens.competitive.add

import androidx.compose.runtime.*
import data.entitys.Competitive
import data.types.ContentSourceType
import screens.BaseAddController
import screens.ViewState
import utils.fileChooser
import utils.toValidName
import utils.toValidOrder

class CompetitiveAddController : BaseAddController<CompetitiveAddState>() {

    override val defaultItemState: CompetitiveAddState = CompetitiveAddState()

    override var state: ViewState<CompetitiveAddState> by mutableStateOf(
        ViewState(
            title = "Add new competitive rank",
            item = defaultItemState
        )
    )

    fun onNameChange(name: String) = setItemState(state.item.copy(name = name.toValidName()))

    fun onOrderChange(order: String) = setItemState(state.item.copy(order = order.toValidOrder()))

    fun onLogoAdd() {
        val newLogo = fileChooser("Select logo", "png") ?: return
        if (!state.item.logo.value.contains(newLogo))
            setItemState(state.item.copy(logo = ContentSourceType.File(newLogo)))
    }

    override suspend fun upload(stateItem: CompetitiveAddState) =
        service.uploadEntity(
            Competitive(
                name = stateItem.name,
                logo = stateItem.logo.value,
                order = stateItem.order
            )
        )
}