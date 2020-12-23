package screens.competitive.edit

import androidx.compose.runtime.*
import data.entitys.Competitive
import screens.BaseEditController
import screens.ViewState
import utils.fileChooser

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
                state.item.copy(
                    logo = entity.logo,
                )
            )
        }
    }

    fun onLogoChange() {
        val newLogo = fileChooser("Select logo", "png") ?: return
        if (!state.item.logo.contains(newLogo)) setItemState(state.item.copy(logo = newLogo))
    }

    override suspend fun update(stateItem: CompetitiveEditState) {
//        TODO("Not yet implemented")
    }
}