package screens.profile_rank.menu

import androidx.compose.runtime.*
import router.NavigationTargets
import screens.BaseController

class ProfileRankMenuController :BaseController<ProfileRankMenuState>() {
    override var _state: ProfileRankMenuState by mutableStateOf(ProfileRankMenuState())

    fun navigateToProfileRankAdd() {
        router.navigateTo(NavigationTargets.ProfileRankAdd)
    }

    fun navigateToProfileRankEdit(id: String) {
        router.navigateTo(NavigationTargets.ProfileRankEdit(id))
    }
}