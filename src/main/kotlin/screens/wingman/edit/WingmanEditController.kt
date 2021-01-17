package screens.wingman.edit

import androidx.compose.runtime.*
import data.entitys.Wingman
import data.types.ContentSourceType
import data.types.FileType
import screens.BaseEditController
import screens.ViewState
import utils.fileChooser
import utils.toValidOrder

class WingmanEditController : BaseEditController<Wingman, WingmanEditItemViewState>() {

    override val defaultItemViewState: WingmanEditItemViewState = WingmanEditItemViewState()

    override var viewState: ViewState<WingmanEditItemViewState> by mutableStateOf(
        ViewState(
            title = "Edit rank",
            item = defaultItemViewState
        )
    )

    fun onLogoChange() {
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

    fun onDelete() {
        launchDeletingEntityOnServer()
    }

    fun onSubmit() {
        launchUpdatingEntityOnServer(viewState.item)
    }

    override suspend fun setEntity() {
        service.getEntityAsync(documentName, Wingman::class).await().let { entity ->
            viewState = viewState.copy(title = "Edit ${entity.name}")
            setItemViewState(
                WingmanEditItemViewState(
                    name = entity.name,
                    logo = ContentSourceType.ContentStorageOriginal(entity.getDocumentName(), entity.logo),
                    order = entity.order
                )
            )
        }
    }

    override fun mapper(itemViewState: WingmanEditItemViewState): Wingman {
        return Wingman(
            name = itemViewState.name,
            logo = itemViewState.logo.value,
            order = itemViewState.order
        )
    }
}