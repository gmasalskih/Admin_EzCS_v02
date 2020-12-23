package screens.wingman.edit

import androidx.compose.runtime.*
import data.entitys.Wingman
import screens.BaseEditController
import screens.ViewState
import utils.fileChooser

class WingmanEditController : BaseEditController<WingmanEditState>() {

    override var state: ViewState<WingmanEditState> by mutableStateOf(
        ViewState(
            title = "Edit rank",
            item = WingmanEditState()
        )
    )

    fun onLogoChange() {
        val newLogo = fileChooser("Select logo", "png") ?: return
        if (!state.item.logo.contains(newLogo)) setItemState(state.item.copy(logo = newLogo))
    }

    override suspend fun setEntity() {
        service.getEntity(documentName, Wingman::class).let { entity ->
            state = state.copy(title = "Edit ${entity.name}")
            setItemState(
                state.item.copy(
                    logo = entity.logo,
                )
            )
        }
    }

    override suspend fun update(stateItem: WingmanEditState) {
//        TODO("Not yet implemented")
    }
}