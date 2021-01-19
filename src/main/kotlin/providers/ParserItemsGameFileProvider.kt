package providers

import data.entitys.blueprint_weapon.BlueprintWeapon

interface ParserItemsGameFileProvider {
    suspend fun getMapOfBlueprintWeapon(pathToSourceFile: String): Map<String, BlueprintWeapon>
}