package screens.weapon.edit

import androidx.compose.runtime.*
import data.entitys.Competitive
import data.entitys.Weapon
import kotlinx.coroutines.launch
import screens.BaseController
import screens.ViewState

class WeaponEditController : BaseController<WeaponEditState>() {
    override var state: ViewState<WeaponEditState> by mutableStateOf(
        ViewState(
            title = "Edit weapon",
            item = WeaponEditState()
        )
    )

    private lateinit var documentName: String
    private lateinit var entity: Weapon

    fun setDocumentName(documentName: String) {
        this.documentName = documentName
    }

    fun onDelete() = cs.launch {
        showLoading()
        service.delete(documentName)
        router.back()
    }

    override fun initState() {
//        TODO("Not yet implemented")
    }
}