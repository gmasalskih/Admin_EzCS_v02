package screens.profile_rank.add

import androidx.compose.runtime.*
import data.entitys.ProfileRank
import data.types.ContentSourceType
import screens.BaseAddController
import screens.ViewState
import utils.fileChooser
import utils.toValidName
import utils.toValidOrder
import utils.toValidXP

class ProfileRankAddController : BaseAddController<ProfileRankAddState>() {
    override var state: ViewState<ProfileRankAddState> by mutableStateOf(
        ViewState(
            title = "Add new profile rank",
            item = ProfileRankAddState()
        )
    )

    override fun onClear() {
        setItemState(ProfileRankAddState())
    }

    override fun onNameChange(name: String) = setItemState(state.item.copy(name = name.toValidName()))

    fun onXPChange(xp: String) = setItemState(state.item.copy(xp = xp.toValidXP()))

    fun onLogoAdd() {
        val newLogo = fileChooser("Select logo", "png") ?: return
        if (!state.item.logo.value.contains(newLogo))
            setItemState(state.item.copy(logo = ContentSourceType.File(newLogo)))
    }

    fun onOrderChange(order: String) = setItemState(state.item.copy(order = order.toValidOrder()))

    override suspend fun upload(stateItem: ProfileRankAddState) {
        service.uploadEntity(
            ProfileRank(
                name = stateItem.name,
                xp = stateItem.xp,
                logo = stateItem.logo.value,
                order = stateItem.order
            )
        )
    }
}