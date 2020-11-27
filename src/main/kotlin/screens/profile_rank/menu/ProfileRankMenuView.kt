package screens.profile_rank.menu

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import common_widgets.CardAdd
import common_widgets.CardWeaponRank
import common_widgets.ScrollableRowAdd
import org.koin.core.inject
import screens.BaseView

class ProfileRankMenuView : BaseView<ProfileRankMenuController>() {
    override val controller by inject<ProfileRankMenuController>()

    private fun navigateToProfileRankAdd() {
        controller.navigateToProfileRankAdd()
    }

    private fun navigateToProfileRankEdit(id: String) {
        controller.navigateToProfileRankEdit(id)
    }

    @Composable
    override fun setContent() {
        ScrollableRowAdd(
            modifier = Modifier.fillMaxWidth(),
            items = controller.getViewState().items,
            cardAdd = { CardAdd(label = "Add new rank", onClick = ::navigateToProfileRankAdd) },
            cardItem = { profileRank ->
                CardWeaponRank(
                    pathToImage = profileRank.logo,
                    name = profileRank.name,
                    onClick = { navigateToProfileRankEdit(profileRank.id) }
                )
            }
        )
    }
}