package screens.weapon.add

import androidx.compose.runtime.*
import data.types.FileType
import screens.BaseAddController
import screens.ViewState
import utils.fileChooser

class WeaponAddController : BaseAddController<WeaponAddState>() {

    override val defaultItemState: WeaponAddState = WeaponAddState()

    override var state: ViewState<WeaponAddState> by mutableStateOf(
        ViewState(
            title = "Add new weapon",
            item = defaultItemState
        )
    )
    var configFile: String by mutableStateOf("")

    fun onNameChange(name: String) {
        setItemState(
            state.item.copy(
                name = name
            )
        )
    }

    fun onImageAdd() {
        val newImage = fileChooser("Select logo", FileType.PNG) ?: return
        if (!state.item.image.contains(newImage)) setItemState(state.item.copy(image = newImage))
    }

    fun onSprayAdd() {
        val newSpray = fileChooser("Select spray", FileType.GIF) ?: return
        if (!state.item.spray.contains(newSpray)) setItemState(state.item.copy(spray = newSpray))
    }

    fun onRecoilAdd() {
        val newRecoil = fileChooser("Select recoil", FileType.GIF) ?: return
        if (!state.item.recoil.contains(newRecoil)) setItemState(state.item.copy(recoil = newRecoil))
    }

    fun onBrowse() {
        val newConfigFile = fileChooser("Select config file", FileType.TXT) ?: return
        if (!configFile.contains(newConfigFile)) configFile = newConfigFile
    }

    fun onParse() {
        //TODO
    }

    fun onSubmit() {
        launchUploadingEntityOnServer(state.item)
    }

    override suspend fun upload(stateItem: WeaponAddState) {
        TODO("Not yet implemented")
    }
}