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

class ProfileRankEditController : BaseEditController<ProfileRank, ProfileRankEditItemViewState>() {

    override val defaultItemViewState: ProfileRankEditItemViewState = ProfileRankEditItemViewState()

    override var viewState: ViewState<ProfileRankEditItemViewState> by mutableStateOf(
        ViewState(
            title = "Edit profile rank",
            item = defaultItemViewState
        )
    )

    override suspend fun setEntity() {
        service.getEntityAsync(documentName, ProfileRank::class).await().let { profileRank ->
            viewState = viewState.copy(title = "Edit ${profileRank.name}")
            setItemViewState(
                convertEntityToItemViewSate(profileRank)
            )
        }
    }

    fun onXPChange(xp: String) {
        setItemViewState(
            viewState.item.copy(
                xp = xp.toValidXP()
            )
        )
    }

    fun onOrderChange(order: String) {
        setItemViewState(
            viewState.item.copy(
                order = order.toValidOrder()
            )
        )
    }

    fun onLogoChange() {
        fileChooser("Select logo", FileType.PNG, viewState.item.logo) { newLogo ->
            setItemViewState(
                viewState.item.copy(
                    logo = newLogo
                )
            )
        }
    }

    fun onDelete() {
        launchDeletingEntityOnServer()
    }

    fun onSubmit() {
        launchUpdatingEntityOnServer(viewState.item)
    }

    override fun convertItemViewSateToEntity(itemViewState: ProfileRankEditItemViewState): ProfileRank {
        return ProfileRank(
            name = itemViewState.name,
            xp = itemViewState.xp,
            logo = itemViewState.logo.value,
            order = itemViewState.order
        )
    }

    override fun convertEntityToItemViewSate(entity: ProfileRank): ProfileRankEditItemViewState {
        return ProfileRankEditItemViewState(
            name = entity.name,
            xp = entity.xp,
            logo = ContentSourceType.ContentStorageOriginal(entity.getDocumentName(), entity.logo),
            order = entity.order,
        )
    }
}