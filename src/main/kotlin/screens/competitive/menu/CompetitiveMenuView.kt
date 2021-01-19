package screens.competitive.menu

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import common_widgets.CardAdd
import common_widgets.CardRank
import common_widgets.ScrollableAddRow
import data.types.ContentSourceType
import org.koin.core.component.inject
import screens.BaseView

class CompetitiveMenuView : BaseView<CompetitiveMenuController>() {
    override val controller by inject<CompetitiveMenuController>()

    @Composable
    override fun setContent(controller: CompetitiveMenuController) {
        ScrollableAddRow(
            modifier = Modifier.fillMaxWidth(),
            items = controller.viewState.item.listCompetitive,
            cardAdd = { CardAdd(label = "Add new rank", onClick = controller::navigateToCompetitiveAdd) },
            cardItem = { competitive ->
                CardRank(
                    pathToImage = ContentSourceType.ContentStorageOriginal(
                        competitive.getDocumentName(),
                        competitive.logo
                    ),
                    name = competitive.name,
                    onClick = { controller.navigateToCompetitiveEdit(competitive.getDocumentName()) }
                )
            }
        )
    }
}