package screens.wingman.edit

import androidx.compose.runtime.*
import data.entitys.Wingman
import data.types.ContentSourceType
import data.types.FileType
import screens.BaseEditController
import screens.ViewState
import utils.fileChooser
import utils.toValidOrder

class WingmanEditController : BaseEditController<WingmanEditState>() {

    override val defaultItemState: WingmanEditState = WingmanEditState()

    override var state: ViewState<WingmanEditState> by mutableStateOf(
        ViewState(
            title = "Edit rank",
            item = defaultItemState
        )
    )

    fun onLogoChange() {
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

    override suspend fun setEntity() {
        service.getEntity(documentName, Wingman::class).let { entity ->
            state = state.copy(title = "Edit ${entity.name}")
            setItemState(
                WingmanEditState(
                    name = entity.name,
                    logo = ContentSourceType.ContentStorageOriginal(entity.getDocumentName(), entity.logo),
                    order = entity.order
                )
            )
        }
    }

    override suspend fun update(stateItem: WingmanEditState) {
        service.updateEntity(
            Wingman(
                name = stateItem.name,
                logo = stateItem.logo.value,
                order = stateItem.order
            )
        )
    }
}