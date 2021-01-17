package screens.weapon.menu

import androidx.compose.runtime.*
import data.entitys.Weapon
import data.types.EntityType
import router.NavigationTargets
import screens.BaseMenuController
import screens.ViewState

class WeaponMenuController : BaseMenuController<WeaponMenuItemViewState>() {

    override val defaultItemViewState: WeaponMenuItemViewState = WeaponMenuItemViewState()

    override var viewState: ViewState<WeaponMenuItemViewState> by mutableStateOf(
        ViewState(
            title = "Weapons",
            item = defaultItemViewState
        )
    )

    fun navigateToWeaponsAdd() {
        router.navigateTo(NavigationTargets.WeaponsAdd)
    }

    fun navigateToWeaponsEdit(id: String) {
        router.navigateTo(NavigationTargets.WeaponsEdit(id))
    }

    override suspend fun setEntity() {
        setItemViewState(
            WeaponMenuItemViewState(
                service.getListEntitiesAsync(EntityType.WEAPON.name, Weapon::class).await()
            )
        )
    }

}