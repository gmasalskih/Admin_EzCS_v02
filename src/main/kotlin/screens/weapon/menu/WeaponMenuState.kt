package screens.weapon.menu

import data.entitys.Weapon
import screens.State

data class WeaponMenuState(
    val listWeapon: List<Weapon> = listOf()
) : State {
    override fun isValid(): Boolean = true
}