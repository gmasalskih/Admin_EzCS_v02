package screens.weapon.edit

import androidx.compose.runtime.*
import screens.BaseEditController
import screens.ViewState

class WeaponEditController : BaseEditController<WeaponEditState>() {

    override val defaultItemState: WeaponEditState = WeaponEditState()

    override var state: ViewState<WeaponEditState> by mutableStateOf(
        ViewState(
            title = "Edit weapon",
            item = defaultItemState
        )
    )

    override suspend fun setEntity() {
//        TODO("Not yet implemented")
    }

    override suspend fun update(stateItem: WeaponEditState) {
//        TODO("Not yet implemented")
    }

}