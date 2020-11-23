package screens.profile_rank.menu

import androidx.compose.runtime.*
import screens.BaseController

class ProfileRankMenuController :BaseController<ProfileRankMenuState>() {
    override var _state: ProfileRankMenuState by mutableStateOf(ProfileRankMenuState())
}