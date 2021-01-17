package screens.weapon.add

import androidx.compose.runtime.*
import data.entitys.Weapon
import data.types.BlueprintWeaponType
import data.types.FileType
import data.types.TeamType
import data.types.WeaponType
import kotlinx.coroutines.launch
import screens.BaseAddController
import screens.ViewState
import utils.fileChooser

class WeaponAddController : BaseAddController<Weapon, WeaponAddItemViewState>() {

    override val defaultItemViewState: WeaponAddItemViewState = WeaponAddItemViewState()

    override var viewState: ViewState<WeaponAddItemViewState> by mutableStateOf(
        ViewState(
            title = "Add new weapon",
            item = defaultItemViewState
        )
    )
    var configFile: String by mutableStateOf("")

    fun onNameChange(weaponName: String) {
        setItemViewState(
            viewState.item.copy(
                weaponName = weaponName
            )
        )
    }

    fun onLogoAdd() {
        fileChooser("Select logo", FileType.PNG, viewState.item.logo) { newLogo ->
            setItemViewState(
                viewState.item.copy(
                    logo = newLogo
                )
            )
        }
    }

    fun onSprayAdd() {
        fileChooser("Select spray", FileType.GIF, viewState.item.spray) { newSpray ->
            setItemViewState(
                viewState.item.copy(
                    spray = newSpray
                )
            )
        }
    }

    fun onRecoilAdd() {
        fileChooser("Select recoil", FileType.GIF, viewState.item.recoil) { newRecoil ->
            setItemViewState(
                viewState.item.copy(
                    recoil = newRecoil
                )
            )
        }
    }

    fun onSubmit() {
        launchUploadingEntityOnServer(viewState.item)
    }

    fun onClear() {

    }

    fun onItemsGameFileSelect() {
        fileChooser(
            "Select items game file",
            FileType.TXT,
            viewState.item.selectedItemsGameFile
        ) { newPathToItemsGameFile ->
            controllerScope.launch {
                service.updateMapOfBlueprintWeaponFromSourceFile(newPathToItemsGameFile.value)
                setItemViewState(
                    viewState.item.copy(
                        listNameOfBlueprintWeapon = service.getListNameOfBlueprintWeaponAsync().await()
                    )
                )
            }
        }
    }

    fun getListOfNameBlueprintWeapon() = viewState.item.listNameOfBlueprintWeapon

    fun isBlueprintWeaponSelected(nameOfBlueprintWeapon: String): Boolean {
        return viewState.item.selectedNameBlueprintWeapon == nameOfBlueprintWeapon
    }


    fun onBlueprintWeaponSelected(nameOfBlueprintWeapon: String) {
        println(
            viewState.item.listNameOfBlueprintWeapon
        )

//        setItemState(
//            state.item.copy(
//                selectedBlueprintWeapon = state.item.mapOfBlueprintWeapon[nameOfBlueprintWeapon]
//            )
//        )
    }

    override fun onViewCreate() {
        super.onViewCreate()
        controllerScope.launch {
            setItemViewState(
                viewState.item.copy(
                    listNameOfBlueprintWeapon = service.getListNameOfBlueprintWeaponAsync().await()
                )
            )
        }
    }



    override fun mapper(itemViewState: WeaponAddItemViewState): Weapon {
        return Weapon()
//        return Weapon(
//            name = itemViewState.weaponName,
//            externalId = itemViewState.selectedNameBlueprintWeapon.first,
//            //TODO check correspondence
//            weaponType = when (blueprintWeapon.visuals.weaponType) {
//                BlueprintWeaponType.Machinegun -> WeaponType.SMG
//                BlueprintWeaponType.Pistol -> WeaponType.PISTOL
//                BlueprintWeaponType.Rifle -> WeaponType.RIFLE
//                BlueprintWeaponType.Shotgun -> WeaponType.HEAVY
//                BlueprintWeaponType.SniperRifle -> WeaponType.HEAVY
//                BlueprintWeaponType.SubMachinegun -> WeaponType.SMG
//                else -> throw Exception("")
//            },
//            teamTypes = mutableListOf<TeamType>().let {
//                if (blueprintWeapon.usedByClasses.terrorists == 1) it.add(TeamType.T)
//                if (blueprintWeapon.usedByClasses.counterTerrorists == 1) it.add(TeamType.CT)
//                it
//            },
//            logo = viewState.item.logo.value,
//            spray = viewState.item.spray.value,
//            recoil = viewState.item.recoil.value,
//            armorRatio = blueprintWeapon.attributes.armorRatio,
//            cycleTime = blueprintWeapon.attributes.cycletime,
//            damage = blueprintWeapon.attributes.damage,
//            inGamePrice = blueprintWeapon.attributes.inGamePrice,
//            inaccuracyCrouch = blueprintWeapon.attributes.inaccuracyCrouch,
//            inaccuracyCrouchAlt = blueprintWeapon.attributes.inaccuracyCrouchAlt,
//            inaccuracyMove = blueprintWeapon.attributes.inaccuracyMove,
//            inaccuracyMoveAlt = blueprintWeapon.attributes.inaccuracyMoveAlt,
//            inaccuracyStand = blueprintWeapon.attributes.inaccuracyStand,
//            inaccuracyStandAlt = blueprintWeapon.attributes.inaccuracyStandAlt,
//            killAward = blueprintWeapon.attributes.killAward,
//            maxPlayerSpeed = blueprintWeapon.attributes.maxPlayerSpeed,
//            penetration = blueprintWeapon.attributes.penetration,
//            primaryClipSize = blueprintWeapon.attributes.primaryClipSize,
//            primaryReserveAmmoMax = blueprintWeapon.attributes.primaryReserveAmmoMax,
//            rangeModifier = blueprintWeapon.attributes.rangeModifier,
//            recoilAngleVariance = blueprintWeapon.attributes.recoilAngleVariance,
//            recoilAngleVarianceAlt = blueprintWeapon.attributes.recoilAngleVarianceAlt,
//            recoilMagnitude = blueprintWeapon.attributes.recoilMagnitude,
//            recoilMagnitudeAlt = blueprintWeapon.attributes.recoilMagnitudeAlt,
//            recoilMagnitudeVariance = blueprintWeapon.attributes.recoilMagnitudeVariance,
//            recoilMagnitudeVarianceAlt = blueprintWeapon.attributes.recoilMagnitudeVarianceAlt,
//            recoveryTimeCrouchFinal = blueprintWeapon.attributes.recoveryTimeCrouchFinal,
//            recoveryTimeStandFinal = blueprintWeapon.attributes.recoveryTimeStandFinal,
//            spread = blueprintWeapon.attributes.spread,
//            spreadAlt = blueprintWeapon.attributes.spreadAlt
//        )
    }
}