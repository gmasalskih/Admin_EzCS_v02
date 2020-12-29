package screens.profile_rank.add

import androidx.compose.runtime.*
import data.entitys.ProfileRank
import data.types.FileType
import screens.BaseAddController
import screens.ViewState
import utils.fileChooser
import utils.toValidName
import utils.toValidOrder
import utils.toValidXP

class ProfileRankAddController : BaseAddController<ProfileRankAddState>() {

    override val defaultItemState: ProfileRankAddState = ProfileRankAddState()

    override var state: ViewState<ProfileRankAddState> by mutableStateOf(
        ViewState(
            title = "new profile rank",
            item = defaultItemState
        )
    )

    fun onNameChange(name: String) {
        setItemState(
            state.item.copy(
                name = name.toValidName()
            )
        )
    }

    fun onXPChange(xp: String) {
        setItemState(
            state.item.copy(
                xp = xp.toValidXP()
            )
        )
    }

    fun onLogoAdd() {
        fileChooser("Select logo", FileType.PNG, state.item.logo) { newLogo ->
            setItemState(
                state.item.copy(
                    logo = newLogo
                )
            )
        }
    }

    fun onOrderChange(order: String) {
        setItemState(
            state.item.copy(
                order = order.toValidOrder()
            )
        )
    }

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