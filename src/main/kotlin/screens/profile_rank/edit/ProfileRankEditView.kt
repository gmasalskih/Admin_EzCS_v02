package screens.profile_rank.edit

import androidx.compose.runtime.Composable
import org.koin.core.inject
import screens.BaseView

class ProfileRankEditView(val id: String) : BaseView<ProfileRankEditController>() {
    override val controller by inject<ProfileRankEditController>()

    @Composable
    override fun render() = renderContent {

    }
}