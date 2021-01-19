package screens.profile_rank.menu

import androidx.compose.runtime.*
import data.entitys.ProfileRank
import data.types.EntityType
import router.NavigationTargets
import screens.BaseMenuController
import screens.ViewState

class ProfileRankMenuController : BaseMenuController<ProfileRankMenuItemViewState>() {

    override val defaultItemViewState: ProfileRankMenuItemViewState = ProfileRankMenuItemViewState()

    override var viewState: ViewState<ProfileRankMenuItemViewState> by mutableStateOf(
        ViewState(
            title = "Profile Rank",
            item = defaultItemViewState
        )
    )

    fun navigateToProfileRankAdd() {
        router.navigateTo(NavigationTargets.ProfileRankAdd)
    }

    fun navigateToProfileRankEdit(documentName: String) {
        router.navigateTo(NavigationTargets.ProfileRankEdit(documentName))
    }

    override suspend fun setEntity() {
        setItemViewState(
            ProfileRankMenuItemViewState(
                service.getListEntitiesAsync(EntityType.PROFILE_RANK.name, ProfileRank::class).await().sortedBy { it.order }
            )
        )
    }
}