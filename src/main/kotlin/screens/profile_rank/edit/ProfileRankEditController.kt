package screens.profile_rank.edit

import androidx.compose.runtime.*
import data.entitys.Competitive
import data.entitys.ProfileRank
import kotlinx.coroutines.launch
import screens.BaseController
import screens.ViewState
import utils.fileChooser

class ProfileRankEditController : BaseController<ProfileRankEditState>() {
    override var state: ViewState<ProfileRankEditState> by mutableStateOf(
        ViewState(
            title = "Edit profile rank",
            item = ProfileRankEditState()
        )
    )

    private lateinit var documentName: String
    private lateinit var entity: ProfileRank

    fun setDocumentName(documentName: String) {
        this.documentName = documentName
    }

    fun onNameChange(name: String) = setItemState(state.item.copy(name = name))

    fun onXPChange(xp: String) = setItemState(state.item.copy(xp = xp))

    fun onLogoAdd() {
        val newLogo = fileChooser("Select logo", "png") ?: return
        if (!state.item.logo.contains(newLogo)) setItemState(state.item.copy(logo = newLogo))
    }

    fun onDelete() = cs.launch {
        showLoading()
        service.delete(documentName)
        router.back()
    }

    fun onSubmit() {
//        TODO("Not yet implemented")
    }

    override fun initState() {
//        TODO("Not yet implemented")
    }
}