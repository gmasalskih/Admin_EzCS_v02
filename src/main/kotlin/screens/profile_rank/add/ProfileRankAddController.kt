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

class ProfileRankAddController : BaseAddController<ProfileRank, ProfileRankAddItemViewState>() {

    override val defaultItemViewState: ProfileRankAddItemViewState = ProfileRankAddItemViewState()

    override var viewState: ViewState<ProfileRankAddItemViewState> by mutableStateOf(
        ViewState(
            title = "new profile rank",
            item = defaultItemViewState
        )
    )

    fun onNameChange(name: String) {
        setItemViewState(
            viewState.item.copy(
                name = name.toValidName()
            )
        )
    }

    fun onXPChange(xp: String) {
        setItemViewState(
            viewState.item.copy(
                xp = xp.toValidXP()
            )
        )
    }

    fun onLogoAdd() {
        fileChooser("Select logo", FileType.PNG, viewState.item.logo) { newLogo ->
            setItemViewState(
                viewState.item.copy(
                    logo = newLogo
                )
            )
        }
    }

    fun onOrderChange(order: String) {
        setItemViewState(
            viewState.item.copy(
                order = order.toValidOrder()
            )
        )
    }

    fun onClear() {
        setDefaultState()
    }

    fun onSubmit() {
        launchUploadingEntityOnServer(viewState.item)
    }

    override fun mapper(itemViewState: ProfileRankAddItemViewState): ProfileRank {
        return ProfileRank(
            name = itemViewState.name,
            xp = itemViewState.xp,
            logo = itemViewState.logo.value,
            order = itemViewState.order
        )
    }
}