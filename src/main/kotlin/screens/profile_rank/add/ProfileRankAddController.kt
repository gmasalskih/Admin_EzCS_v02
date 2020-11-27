package screens.profile_rank.add

import androidx.compose.runtime.*
import screens.BaseController

class ProfileRankAddController:BaseController<ProfileRankAddState>() {
    override var _state: ProfileRankAddState by mutableStateOf(ProfileRankAddState())

    fun clearState(){
        _state = ProfileRankAddState()
    }
}