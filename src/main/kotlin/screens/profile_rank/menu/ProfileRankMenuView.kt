package screens.profile_rank.menu

import androidx.compose.runtime.Composable
import org.koin.core.inject
import screens.BaseView

class ProfileRankMenuView : BaseView<ProfileRankMenuController>() {
    override val controller by inject<ProfileRankMenuController>()
}