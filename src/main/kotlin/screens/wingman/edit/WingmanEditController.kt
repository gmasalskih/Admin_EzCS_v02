package screens.wingman.edit

import androidx.compose.runtime.*
import data.entitys.Competitive
import data.entitys.Wingman
import kotlinx.coroutines.launch
import screens.BaseController
import screens.ViewState
import utils.fileChooser

class WingmanEditController : BaseController<WingmanEditState>() {

    override var state: ViewState<WingmanEditState> by mutableStateOf(ViewState(title = "Edit rank", item = WingmanEditState()))

    private lateinit var documentName: String
    private lateinit var entity: Wingman

    fun setDocumentName(documentName: String) {
        this.documentName = documentName
    }

    fun onNameChange(name: String) = setItemState(state.item.copy(name = name))

    fun onLogoChange() {
        val newLogo = fileChooser("Select logo", "png") ?: return
        if (!state.item.logo.contains(newLogo)) setItemState(state.item.copy(logo = newLogo))
    }

    fun onDelete() = cs.launch {
        showLoading()
        service.delete(documentName)
        router.back()
    }

    fun onSubmit() {
        //TODO implement fun onSubmit
    }

    override fun initState() {
//        TODO("Not yet implemented")
    }
}