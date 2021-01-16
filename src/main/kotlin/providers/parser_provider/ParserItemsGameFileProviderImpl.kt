package providers.parser_provider

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import data.entitys.blueprint_weapon.BlueprintWeapon
import data.types.BlueprintWeaponType
import providers.ParserItemsGameFileProvider
import utils.*
import java.io.File

class ParserItemsGameFileProviderImpl private constructor(private var json: String) : ParserItemsGameFileProvider {

    private val gson = Gson()
    private val mapOfWeaponRaw = mutableMapOf<String, BlueprintWeapon>()

    init {
        if (!json.isJSON()) throw IllegalArgumentException("Invalid json format")
        makeMapOfWeaponRaw()
    }

    companion object {
        //TODO HardCode
        fun getInstance(pathToSourceFile: String): ParserItemsGameFileProviderImpl {
            val sourceTxt = File(pathToSourceFile)
            val sb = StringBuilder()
            sb.append("{")
            var isReadyToParse = false
            var curlyBracketCount = 0
            sourceTxt.forEachLine {
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
            return ParserItemsGameFileProviderImpl(sb.replace(",}".toRegex(), "}"))
        }
    }

    override fun saveAsFile(pathToTargetFile: String) {
        File(pathToTargetFile).writeText(gson.toJson(mapOfWeaponRaw))
    }

    override fun getMapOfWeaponRaw(): Map<String, BlueprintWeapon> = mapOfWeaponRaw

    private fun makeMapOfWeaponRaw() {
        getSubJson(JsonParser.parseString(json)) { key, value ->
            gson.fromJson(value, BlueprintWeapon::class.java).let { weaponRaw ->
                weaponRaw?.visuals?.weaponType?.let { weaponRawType ->
                    when (weaponRawType) {
                        BlueprintWeaponType.Machinegun.value -> mapOfWeaponRaw[key] = weaponRaw
                        BlueprintWeaponType.Pistol.value -> mapOfWeaponRaw[key] = weaponRaw
                        BlueprintWeaponType.Rifle.value -> mapOfWeaponRaw[key] = weaponRaw
                        BlueprintWeaponType.Shotgun.value -> mapOfWeaponRaw[key] = weaponRaw
                        BlueprintWeaponType.SniperRifle.value -> mapOfWeaponRaw[key] = weaponRaw
                        BlueprintWeaponType.SubMachinegun.value -> mapOfWeaponRaw[key] = weaponRaw
                    }
                }
            }
        }
    }

    private fun getSubJson(jsonElement: JsonElement, block: (String, JsonElement) -> Unit) {
        if (jsonElement.isJsonObject) {
            jsonElement.asJsonObject.entrySet().forEach { map ->
                block(map.key, map.value)
            }
        }
    }
}