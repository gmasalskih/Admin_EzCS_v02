package providers

import data.entitys.blueprint_weapon.BlueprintWeapon

interface ParserItemsGameFileProvider {
    fun saveAsFile(pathToTargetFile: String)
    fun getMapOfWeaponRaw(): Map<String, BlueprintWeapon>
}