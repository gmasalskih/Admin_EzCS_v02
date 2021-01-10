package screens.competitive.add

import androidx.compose.runtime.*
import data.entitys.Competitive
import data.types.FileType
import kotlinx.coroutines.launch
import org.koin.core.component.KoinApiExtension
import screens.BaseAddController
import screens.ViewState
import utils.fileChooser
import utils.toValidName
import utils.toValidOrder

class CompetitiveAddController : BaseAddController<CompetitiveAddState>() {

    override val defaultItemState: CompetitiveAddState = CompetitiveAddState()

    override var state: ViewState<CompetitiveAddState> by mutableStateOf(
        ViewState(
            title = "new competitive rank",
            item = defaultItemState
        )
    )

    fun onNameChange(name: String) {
        setItemState(
            state.item.copy(
                name = name.toValidName()
            )
        )
    }

    fun onOrderChange(order: String) {
        setItemState(
            state.item.copy(
                order = order.toValidOrder()
            )
        )
    }

    fun onLogoAdd() {
        fileChooser("Select logo", FileType.PNG, state.item.logo) { newLogo ->
            setItemState(
                state.item.copy(
                    logo = newLogo
                )
            )
        }
    }

    override suspend fun upload(stateItem: CompetitiveAddState) {
        service.uploadEntity(
            Competitive(
                name = stateItem.name,
                logo = stateItem.logo.value,
                order = stateItem.order
            )
        )
    }
}