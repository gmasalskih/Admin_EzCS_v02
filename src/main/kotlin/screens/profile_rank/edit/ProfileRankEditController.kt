package screens.profile_rank.edit

import androidx.compose.runtime.*
import data.entitys.ProfileRank
import screens.BaseEditController
import screens.ViewState
import utils.fileChooser
import utils.toValidXP

class ProfileRankEditController : BaseEditController<ProfileRankEditState>() {
    override var state: ViewState<ProfileRankEditState> by mutableStateOf(
        ViewState(
            title = "Edit profile rank",
            item = ProfileRankEditState()
        )
    )

    override suspend fun setEntity() {
        service.getEntity(documentName, ProfileRank::class).let { entity ->
            state = state.copy(title = "Edit ${entity.name}")
            setItemState(
                state.item.copy(
                    logo = entity.logo,
                    xp = entity.xp
                )
            )
        }
    }

    fun onXPChange(xp: String) = setItemState(state.item.copy(xp = xp.toValidXP()))

    fun onLogoChange() {
        val newLogo = fileChooser("Select logo", "png") ?: return
        if (!state.item.logo.contains(newLogo)) setItemState(state.item.copy(logo = newLogo))
    }

    override suspend fun update(stateItem: ProfileRankEditState) {
//        TODO("Not yet implemented")
    }
}