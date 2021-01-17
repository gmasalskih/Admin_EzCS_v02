package providers.parser_provider

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import data.entitys.blueprint_weapon.BlueprintWeapon
import data.types.BlueprintWeaponType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import providers.ParserItemsGameFileProvider
import utils.*
import java.io.File

class ParserItemsGameFileProviderImpl(private val gson: Gson) : ParserItemsGameFileProvider {

    override suspend fun getMapOfBlueprintWeapon(pathToSourceFile: String): Map<String, BlueprintWeapon> {
        val json  = withContext(Dispatchers.Default){ getJsonFromFile(pathToSourceFile) }
        val mapOfWeaponRaw = mutableMapOf<String, BlueprintWeapon>()
        getSubJson(JsonParser.parseString(json)) { key, value ->
            gson.fromJson(value, BlueprintWeapon::class.java).let { blueprintWeapon ->
                blueprintWeapon?.visuals?.weaponType?.let { weaponType ->
                    when (weaponType) {
                        BlueprintWeaponType.Machinegun -> mapOfWeaponRaw[key] = blueprintWeapon
                        BlueprintWeaponType.Pistol -> mapOfWeaponRaw[key] = blueprintWeapon
                        BlueprintWeaponType.Rifle -> mapOfWeaponRaw[key] = blueprintWeapon
                        BlueprintWeaponType.Shotgun -> mapOfWeaponRaw[key] = blueprintWeapon
                        BlueprintWeaponType.SniperRifle -> mapOfWeaponRaw[key] = blueprintWeapon
                        BlueprintWeaponType.SubMachinegun -> mapOfWeaponRaw[key] = blueprintWeapon
                    }
                }
            }
        }
        return mapOfWeaponRaw
    }

    private fun getJsonFromFile(pathToSourceFile: String): String {
        val file = File(pathToSourceFile)
        val sb = StringBuilder()
        sb.append("{")
        var isReadyToParse = false
        var curlyBracketCount = 0
        file.forEachLine {
            val str = it.trim()
            if (str.matches(Regex("\"((statted_item_base)|(weapon_[a-zA-Z0-9_]+_prefab))\"")))
                isReadyToParse = true
            if (isReadyToParse) {
                when {
                    str.isObjName() -> {
                        when {
                            str.matches("\"statted_item_base\"".toRegex()) -> {
                                sb.append(
                                    "${
                                        str.replace("\"statted_item_base\"".toRegex(), "\"default\"").toObjName()
                                    }:"
                                )
                            }
                            str.matches("\"weapon_[a-zA-Z0-9_]+_prefab\"".toRegex()) -> {
                                sb.append("${str.replace("((weapon_)|(_prefab))".toRegex(), "").toObjName()}:")
                            }
                            else -> sb.append("${str.toObjName()}:")
                        }
                    }
                    str.isOpenCurlyBracket() -> {
                        sb.append("{")
                        curlyBracketCount++
                    }
                    str.isCloseCurlyBracket() -> {
                        sb.append("$str,")
                        curlyBracketCount--
                        if (curlyBracketCount == 0) isReadyToParse = false
                    }
                    str.isObjField() -> sb.append("${str.toObjField()},")
                }
            }
        }
        sb.append("}")
        return sb.replace(",}".toRegex(), "}")
    }

    private fun getSubJson(jsonElement: JsonElement, block: (String, JsonElement) -> Unit) {
        if (jsonElement.isJsonObject) {
            jsonElement.asJsonObject.entrySet().forEach { map ->
                block(map.key, map.value)
            }
        }
    }
}