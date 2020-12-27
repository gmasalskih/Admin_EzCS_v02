package screens.profile_rank.menu

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import common_widgets.CardAdd
import common_widgets.CardRank
import common_widgets.ScrollableRowAdd
import data.types.ContentSourceType
import org.koin.core.inject
import screens.BaseView

class ProfileRankMenuView : BaseView<ProfileRankMenuController>() {
    override val controller by inject<ProfileRankMenuController>()

    @Composable
    override fun setContent(controller: ProfileRankMenuController) {
        ScrollableRowAdd(
            modifier = Modifier.fillMaxWidth(),
            items = controller.getViewState().item.listProfileRank,
            cardAdd = { CardAdd(label = "Add new rank", onClick = controller::navigateToProfileRankAdd) },
            cardItem = { profileRank ->
                CardRank(
                    pathToImage = ContentSourceType.ContentStorageOriginal(
                        profileRank.getDocumentName(),
                        profileRank.logo
                    ),
                    name = profileRank.name,
                    onClick = { controller.navigateToProfileRankEdit(profileRank.getDocumentName()) }
                )
            }
        )
    }
}