package screens.profile_rank.menu

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import common_widgets.CardAdd
import common_widgets.CardRank
import common_widgets.ScrollableAddRow
import data.types.ContentSourceType
import org.koin.core.component.inject
import screens.BaseView

class ProfileRankMenuView : BaseView<ProfileRankMenuController>() {
    override val controller by inject<ProfileRankMenuController>()

    @Composable
    override fun setContent(controller: ProfileRankMenuController) {
        ScrollableAddRow(
            modifier = Modifier.fillMaxWidth(),
            items = controller.viewState.item.listProfileRank,
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