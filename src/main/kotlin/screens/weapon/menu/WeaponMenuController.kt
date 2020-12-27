package screens.weapon.menu

import androidx.compose.runtime.*
import data.entitys.Weapon
import data.types.EntityType
import router.NavigationTargets
import screens.BaseMenuController
import screens.ViewState

class WeaponMenuController : BaseMenuController<WeaponMenuState>() {

    override val defaultItemState: WeaponMenuState = WeaponMenuState()

    override var state: ViewState<WeaponMenuState> by mutableStateOf(
        ViewState(
            title = "Weapons",
            item = defaultItemState
        )
    )

    fun navigateToWeaponsAdd() {
        router.navigateTo(NavigationTargets.WeaponsAdd)
    }

    fun navigateToWeaponsEdit(id: String) {
        router.navigateTo(NavigationTargets.WeaponsEdit(id))
    }

    override suspend fun setEntity() {
        setItemState(
            WeaponMenuState(
                service.getListEntities(EntityType.WEAPON.name, Weapon::class)
            )
        )
    }

}