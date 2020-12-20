package screens.profile_rank.edit

import androidx.compose.runtime.*
import data.entitys.ProfileRank
import screens.BaseEditController
import screens.ViewState
import utils.fileChooser
import utils.isValidPathToFile
import utils.toValidXP

class ProfileRankEditController : BaseEditController<ProfileRank, ProfileRankEditState>() {
    override var state: ViewState<ProfileRankEditState> by mutableStateOf(
        ViewState(
            title = "Edit profile rank",
            item = ProfileRankEditState()
        )
    )

    override suspend fun setRowEntity() {
        entity = service.retrieveRawEntity(documentName, ProfileRank::class)
    }

    override suspend fun setEntity() {
        service.retrieveEntity(documentName, ProfileRank::class).let { entity ->
            state = state.copy(title = "Edit ${entity.name}")
            setItemState(
                state.item.copy(
                    logo = entity.logo,
                    xp = entity.xp
                )
            )
        }
    }

    override suspend fun update() {
        service.update(
            entity.copy(
                logo = if (state.item.logo.isValidPathToFile()) state.item.logo else entity.logo,
                xp = state.item.xp
            )
        )
    }

    fun onXPChange(xp: String) = setItemState(state.item.copy(xp = xp.toValidXP()))

    fun onLogoChange() {
        val newLogo = fileChooser("Select logo", "png") ?: return
        if (!state.item.logo.contains(newLogo)) setItemState(state.item.copy(logo = newLogo))
    }
}