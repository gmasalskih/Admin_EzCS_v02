package screens.profile_rank.add

import androidx.compose.runtime.*
import data.pojo.ProfileRank
import screens.BaseController
import screens.ViewState
import utils.fileChooser

class ProfileRankAddController : BaseController<ProfileRank>() {
    override var state: ViewState<ProfileRank> by mutableStateOf(
        ViewState(
            title = "Add new profile rank",
            item = ProfileRank()
        )
    )

    fun onClear() {
        state = ViewState(title = "Add new profile rank", item = ProfileRank())
    }

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