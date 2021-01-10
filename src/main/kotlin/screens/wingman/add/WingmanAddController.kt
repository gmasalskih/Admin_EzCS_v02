package screens.wingman.add

import androidx.compose.runtime.*
import data.entitys.Wingman
import data.types.FileType
import screens.BaseAddController
import screens.ViewState
import utils.fileChooser
import utils.toValidName
import utils.toValidOrder

class WingmanAddController : BaseAddController<WingmanAddState>() {

    override val defaultItemState: WingmanAddState = WingmanAddState()

    override var state: ViewState<WingmanAddState> by mutableStateOf(
        ViewState(
            title = "new wingman rank",
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

    fun onLogoAdd() {
        fileChooser("Select logo", FileType.PNG, state.item.logo) { newLogo ->
            setItemState(
                state.item.copy(
                    logo = newLogo
                )
            )
        }
    }

    fun onOrderChange(order: String) {
        setItemState(
            state.item.copy(
                order = order.toValidOrder()
            )
        )
    }

    fun onClear(){
        setDefaultState()
    }

    fun onSubmit() {
        launchUploadingEntityOnServer(state.item)
    }

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