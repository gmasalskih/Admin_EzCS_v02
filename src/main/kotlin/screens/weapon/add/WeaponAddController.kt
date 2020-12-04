package screens.weapon.add

import androidx.compose.runtime.*
import data.entitys.Weapon
import screens.BaseController
import screens.ViewState
import utils.fileChooser

class WeaponAddController : BaseController<WeaponAddState>() {
    override var state: ViewState<WeaponAddState> by mutableStateOf(ViewState(title = "Add new weapon", item = WeaponAddState()))
    var configFile: String by mutableStateOf("")

    fun onNameChange(name: String) = setItemState(state.item.copy(name = name))

    fun onImageAdd() {
        val newImage = fileChooser("Select logo", "png") ?: return
        if (!state.item.image.contains(newImage)) setItemState(state.item.copy(image = newImage))
    }

    fun onSprayAdd() {
        val newSpray = fileChooser("Select spray", "gif") ?: return
        if (!state.item.spray.contains(newSpray)) setItemState(state.item.copy(spray = newSpray))
    }

    fun onRecoilAdd() {
        val newRecoil = fileChooser("Select recoil", "gif") ?: return
        if (!state.item.recoil.contains(newRecoil)) setItemState(state.item.copy(recoil = newRecoil))
    }

    fun onBrowse() {
        val newConfigFile = fileChooser("Select config file", "txt") ?: return
        if (!configFile.contains(newConfigFile)) configFile = newConfigFile
    }

    fun onClear() {
        state = ViewState(title = "Add new weapon", item = WeaponAddState())
        configFile = ""
    }

    fun onSubmit() {
        //TODO
    }

    fun onParse() {
        //TODO
    }
}