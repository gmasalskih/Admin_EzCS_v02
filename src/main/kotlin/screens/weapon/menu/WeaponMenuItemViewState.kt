package screens.weapon.menu

import data.entitys.Weapon
import screens.ItemViewState

data class WeaponMenuItemViewState(
    val listWeapon: List<Weapon> = listOf()
) : ItemViewState {
    override fun isValid(): Boolean = true
}