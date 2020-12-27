package screens.profile_rank.menu

import androidx.compose.runtime.*
import data.entitys.ProfileRank
import data.types.EntityType
import router.NavigationTargets
import screens.BaseMenuController
import screens.ViewState

class ProfileRankMenuController : BaseMenuController<ProfileRankMenuState>() {

    override val defaultItemState: ProfileRankMenuState = ProfileRankMenuState()

    override var state: ViewState<ProfileRankMenuState> by mutableStateOf(
        ViewState(
            title = "Profile Rank",
            item = defaultItemState
        )
    )

    fun navigateToProfileRankAdd() {
        router.navigateTo(NavigationTargets.ProfileRankAdd)
    }

    fun navigateToProfileRankEdit(documentName: String) {
        router.navigateTo(NavigationTargets.ProfileRankEdit(documentName))
    }

    override suspend fun setEntity() {
        setItemState(
            ProfileRankMenuState(
                service.getListEntities(EntityType.PROFILE_RANK.name, ProfileRank::class).sortedBy { it.order }
            )
        )
    }
}