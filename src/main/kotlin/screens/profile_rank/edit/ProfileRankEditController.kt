package screens.profile_rank.edit

import androidx.compose.runtime.*
import data.entitys.ProfileRank
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

    fun onNameChange(name: String) = setItemState(state.item.copy(name = name))

    fun onXPChange(xp: String) = setItemState(state.item.copy(xp = xp))

    fun onLogoAdd() {
        val newLogo = fileChooser("Select logo", "png") ?: return
        if (!state.item.logo.contains(newLogo)) setItemState(state.item.copy(logo = newLogo))
    }

    fun onSubmit() {
//        TODO("Not yet implemented")
    }
}