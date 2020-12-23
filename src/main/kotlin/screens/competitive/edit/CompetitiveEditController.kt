package screens.competitive.edit

import androidx.compose.runtime.*
import data.entitys.Competitive
import data.types.ContentSourceType
import screens.BaseEditController
import screens.ViewState
import utils.fileChooser
import utils.toValidOrder

class CompetitiveEditController : BaseEditController<CompetitiveEditState>() {
    override var state: ViewState<CompetitiveEditState> by mutableStateOf(
        ViewState(
            title = "Edit rank",
            item = CompetitiveEditState()
        )
    )

    override suspend fun setEntity() {
        service.getEntity(documentName, Competitive::class).let { entity ->
            state = state.copy(title = "Edit ${entity.name}")
            setItemState(
                CompetitiveEditState(
                    name = entity.name,
                    logo = ContentSourceType.ContentStorageThumbnail(entity.getDocumentName(), entity.logo),
                    order = entity.order
                )
            )
        }
    }

    fun onOrderChange(order: String) {
        setItemState(state.item.copy(order = order.toValidOrder()))
    }

    fun onLogoChange() {
        val newLogo = fileChooser("Select logo", "png") ?: return
        if (!state.item.logo.value.contains(newLogo))
            setItemState(state.item.copy(logo = ContentSourceType.File(newLogo)))
    }

    override suspend fun update(stateItem: CompetitiveEditState) {
        service.updateEntity(
            Competitive(
                name = stateItem.name,
                logo = stateItem.logo.value,
                order = stateItem.order
            )
        )
    }
}