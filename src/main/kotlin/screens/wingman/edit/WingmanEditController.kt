package screens.wingman.edit

import androidx.compose.runtime.*
import data.entitys.Wingman
import data.types.ContentSourceType
import screens.BaseEditController
import screens.ViewState
import utils.fileChooser
import utils.toValidOrder

class WingmanEditController : BaseEditController<WingmanEditState>() {

    override var state: ViewState<WingmanEditState> by mutableStateOf(
        ViewState(
            title = "Edit rank",
            item = WingmanEditState()
        )
    )

    fun onLogoChange() {
        val newLogo = fileChooser("Select logo", "png") ?: return
        if (!state.item.logo.value.contains(newLogo))
            setItemState(state.item.copy(logo = ContentSourceType.File(newLogo)))
    }

    fun onOrderChange(order: String) {
        setItemState(state.item.copy(order = order.toValidOrder()))
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