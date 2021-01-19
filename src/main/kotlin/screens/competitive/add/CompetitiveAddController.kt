package screens.competitive.add

import androidx.compose.runtime.*
import data.entitys.Competitive
import data.types.FileType
import screens.BaseAddController
import screens.ViewState
import utils.fileChooser
import utils.toValidName
import utils.toValidOrder

class CompetitiveAddController : BaseAddController<Competitive,CompetitiveAddItemViewState>() {

    override val defaultItemViewState: CompetitiveAddItemViewState = CompetitiveAddItemViewState()

    override var viewState: ViewState<CompetitiveAddItemViewState> by mutableStateOf(
        ViewState(
            title = "new competitive rank",
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

    fun onOrderChange(order: String) {
        setItemViewState(
            viewState.item.copy(
                order = order.toValidOrder()
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

    fun onClear(){
        setDefaultState()
    }

    fun onSubmit() {
        launchUploadingEntityOnServer(viewState.item)
    }

    override fun convertItemViewSateToEntity(itemViewState: CompetitiveAddItemViewState): Competitive {
        return Competitive(
            name = itemViewState.name,
            logo = itemViewState.logo.value,
            order = itemViewState.order
        )
    }
}