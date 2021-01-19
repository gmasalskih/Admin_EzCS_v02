package screens.wingman.add

import androidx.compose.runtime.*
import data.entitys.Wingman
import data.types.FileType
import screens.BaseAddController
import screens.ViewState
import utils.fileChooser
import utils.toValidName
import utils.toValidOrder

class WingmanAddController : BaseAddController<Wingman, WingmanAddItemViewState>() {

    override val defaultItemViewState: WingmanAddItemViewState = WingmanAddItemViewState()

    override var viewState: ViewState<WingmanAddItemViewState> by mutableStateOf(
        ViewState(
            title = "new wingman rank",
            item = defaultItemViewState
        )
    )

    fun onNameChange(name: String) {
        setItemViewState(
            viewState.item.copy(
                name = name.toValidName()
            )
        )
    }

    fun onLogoAdd() {
        fileChooser("Select logo", FileType.PNG, viewState.item.logo) { newLogo ->
            setItemViewState(
                viewState.item.copy(
                    logo = newLogo
                )
            )
        }
    }

    fun onOrderChange(order: String) {
        setItemViewState(
            viewState.item.copy(
                order = order.toValidOrder()
            )
        )
    }

    fun onClear() {
        setDefaultState()
    }

    fun onSubmit() {
        launchUploadingEntityOnServer(viewState.item)
    }

    override fun convertItemViewSateToEntity(itemViewState: WingmanAddItemViewState): Wingman {
        return Wingman(
            name = itemViewState.name,
            logo = itemViewState.logo.value,
            order = itemViewState.order
        )
    }
}