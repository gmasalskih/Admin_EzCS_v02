package screens.profile_rank.edit

import androidx.compose.runtime.*
import data.entitys.ProfileRank
import data.types.ContentSourceType
import data.types.FileType
import screens.BaseEditController
import screens.ViewState
import utils.fileChooser
import utils.toValidOrder
import utils.toValidXP

class ProfileRankEditController : BaseEditController<ProfileRankEditState>() {

    override val defaultItemState: ProfileRankEditState = ProfileRankEditState()

    override var state: ViewState<ProfileRankEditState> by mutableStateOf(
        ViewState(
            title = "Edit profile rank",
            item = defaultItemState
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

    fun onXPChange(xp: String) {
        setItemState(
            state.item.copy(
                xp = xp.toValidXP()
            )
        )
    }

    fun onOrderChange(order: String) {
        setItemState(
            state.item.copy(
                order = order.toValidOrder()
            )
        )
    }

    fun onLogoChange() {
        fileChooser("Select logo", FileType.PNG, state.item.logo) { newLogo ->
            setItemState(
                state.item.copy(
                    logo = newLogo
                )
            )
        }
    }

    fun onDelete(){
        launchDeletingEntityOnServer()
    }

    fun onSubmit() {
        launchUpdatingEntityOnServer(state.item)
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