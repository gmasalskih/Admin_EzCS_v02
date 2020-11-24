package screens.profile_rank.add

import androidx.compose.runtime.Composable
import org.koin.core.inject
import screens.BaseView

class ProfileRankAddView : BaseView<ProfileRankAddController>() {
    override val controller by inject<ProfileRankAddController>()

    @Composable
    override fun setContent() {
//        TODO("Not yet implemented")
    }
}