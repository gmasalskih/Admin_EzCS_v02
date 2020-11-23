package screens.profile_rank.edit

import androidx.compose.runtime.*
import screens.BaseController

class ProfileRankEditController : BaseController<ProfileRankEditSate>() {
    override var _state: ProfileRankEditSate by mutableStateOf(ProfileRankEditSate())
}