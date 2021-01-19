package screens.weapon.add

import data.entitys.blueprint_weapon.BlueprintWeapon
import data.types.ContentSourceType
import screens.ItemViewState

data class WeaponAddItemViewState(
    val weaponName: String = "",
    val selectedItemsGameFile: ContentSourceType = ContentSourceType.Empty,
    val mapOfBlueprintWeapon: Map<String, BlueprintWeapon> = mapOf(),
    val selectedBlueprintWeapon: Pair<String, BlueprintWeapon?> = "" to null,
    val logo: ContentSourceType = ContentSourceType.Empty,
    val spray: ContentSourceType = ContentSourceType.Empty,
    val recoil: ContentSourceType = ContentSourceType.Empty,
) : ItemViewState {
    override fun isValid() = weaponName.isNotEmpty() &&
            selectedBlueprintWeapon.first.isNotEmpty() &&
            selectedBlueprintWeapon.second != null &&
            logo !is ContentSourceType.Empty &&
            spray !is ContentSourceType.Empty &&
            recoil !is ContentSourceType.Empty
}