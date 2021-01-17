package screens.weapon.add

import data.types.ContentSourceType
import screens.ItemViewState

data class WeaponAddItemViewState(
    val weaponName: String = "",
    val weaponDescription:String = "",
    val selectedItemsGameFile: ContentSourceType = ContentSourceType.Empty,
    val listNameOfBlueprintWeapon: List<String> = listOf(),
    val selectedNameBlueprintWeapon: String = "",
    val logo: ContentSourceType = ContentSourceType.Empty,
    val spray: ContentSourceType = ContentSourceType.Empty,
    val recoil: ContentSourceType = ContentSourceType.Empty,
) : ItemViewState {
    override fun isValid() = weaponName.isNotEmpty() &&
            selectedNameBlueprintWeapon.isNotEmpty() &&
            logo !is ContentSourceType.Empty &&
            spray !is ContentSourceType.Empty &&
            recoil !is ContentSourceType.Empty
}