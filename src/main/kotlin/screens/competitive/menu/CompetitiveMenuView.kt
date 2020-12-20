package screens.competitive.menu

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import common_widgets.CardAdd
import common_widgets.CardRank
import common_widgets.ScrollableRowAdd
import org.koin.core.inject
import screens.BaseView

class CompetitiveMenuView : BaseView<CompetitiveMenuController>() {
    override val controller by inject<CompetitiveMenuController>()

    @Composable
    override fun setContent(controller: CompetitiveMenuController) {
        ScrollableRowAdd(
            modifier = Modifier.fillMaxWidth(),
            items = controller.getViewState().item,
            cardAdd = { CardAdd(label = "Add new rank", onClick = controller::navigateToCompetitiveAdd) },
            cardItem = { competitiveRank ->
                CardRank(
                    pathToImage = competitiveRank.logo,
                    name = competitiveRank.name,
                    onClick = { controller.navigateToCompetitiveEdit(competitiveRank.getDocumentName()) }
                )
            }
        )
    }
}