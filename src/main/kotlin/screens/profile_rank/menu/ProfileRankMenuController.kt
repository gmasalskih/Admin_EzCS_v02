package screens.profile_rank.menu

import androidx.compose.runtime.*
import data.entitys.ProfileRank
import router.NavigationTargets
import screens.BaseController
import screens.ViewState

class ProfileRankMenuController : BaseController<List<ProfileRank>>() {
    override var state: ViewState<List<ProfileRank>> by mutableStateOf(
        ViewState(
            title = "Profile Rank", item = listOf(
                ProfileRank(
                    name = "Private Rank 1",
                    logo = ""
                ),
                ProfileRank(
                    name = "Private Rank 2",
                    logo = ""
                ),
                ProfileRank(
                    name = "Private Rank 3",
                    logo = ""
                ),
            )
        )
    )

    fun navigateToProfileRankAdd() {
        router.navigateTo(NavigationTargets.ProfileRankAdd)
    }

    fun navigateToProfileRankEdit(id: String) {
        router.navigateTo(NavigationTargets.ProfileRankEdit(id))
    }
}