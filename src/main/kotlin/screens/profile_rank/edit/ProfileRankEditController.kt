package screens.profile_rank.edit

import androidx.compose.runtime.*
import data.entitys.ProfileRank
import data.types.ContentSourceType
import screens.BaseEditController
import screens.ViewState
import utils.fileChooser
import utils.toValidOrder
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
                ProfileRankEditState(
                    name = entity.name,
                    xp = entity.xp,
                    logo = ContentSourceType.ContentStorageOriginal(entity.getDocumentName(), entity.logo),
                    order = entity.order,
                )
            )
        }
    }

    fun onXPChange(xp: String) = setItemState(state.item.copy(xp = xp.toValidXP()))

    fun onOrderChange(order: String) = setItemState(state.item.copy(order = order.toValidOrder()))

    fun onLogoChange() {
        val newLogo = fileChooser("Select logo", "png") ?: return
        if (!state.item.logo.value.contains(newLogo))
            setItemState(state.item.copy(logo = ContentSourceType.File(newLogo)))
    }

    override suspend fun update(stateItem: ProfileRankEditState) {
        service.updateEntity(
            ProfileRank(
                name = stateItem.name,
                xp = stateItem.xp,
                logo = stateItem.logo.value,
                order = stateItem.order
            )
        )
    }
}