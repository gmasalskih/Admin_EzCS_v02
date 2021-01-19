package screens.weapon.edit

import data.entitys.Weapon
import data.types.ContentSourceType
import screens.ItemViewState

data class WeaponEditItemViewState(

    val logo: ContentSourceType = ContentSourceType.Empty,
    val spray: ContentSourceType = ContentSourceType.Empty,
    val recoil: ContentSourceType = ContentSourceType.Empty,
    val weapon: Weapon? = null

) : ItemViewState {
    override fun isValid(): Boolean {
        return logo !is ContentSourceType.Empty &&
                recoil !is ContentSourceType.Empty &&
                spray !is ContentSourceType.Empty &&
                weapon != null
    }
}