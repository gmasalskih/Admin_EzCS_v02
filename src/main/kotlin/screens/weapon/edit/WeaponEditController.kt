package screens.weapon.edit

import androidx.compose.runtime.*
import data.entitys.Weapon
import screens.BaseEditController
import screens.ViewState

class WeaponEditController : BaseEditController<Weapon, WeaponEditItemViewState>() {

    override val defaultItemViewState: WeaponEditItemViewState = WeaponEditItemViewState()

    override var viewState: ViewState<WeaponEditItemViewState> by mutableStateOf(
        ViewState(
            title = "Edit weapon",
            item = defaultItemViewState
        )
    )

    fun onDelete() {
        launchDeletingEntityOnServer()
    }

    fun onSubmit() {
        launchUpdatingEntityOnServer(viewState.item)
    }

    override suspend fun setEntity() {
//        TODO("Not yet implemented")
    }

    override fun mapper(itemViewState: WeaponEditItemViewState): Weapon {
        TODO("Not yet implemented")
    }


}