package screens.competitive.menu

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import common_widgets.CardAdd
import common_widgets.CardWeaponRank
import common_widgets.ScrollableRowAdd
import org.koin.core.inject
import screens.BaseView

class CompetitiveMenuView : BaseView<CompetitiveMenuController>() {
    override val controller by inject<CompetitiveMenuController>()

    private fun navigateToCompetitiveAdd() {
        controller.navigateToCompetitiveAdd()
    }

    private fun navigateToCompetitiveEdit(id: String) {
        controller.navigateToCompetitiveEdit(id)
    }

    @Composable
    override fun setContent() {
        ScrollableRowAdd(
            modifier = Modifier.fillMaxWidth(),
            items = controller.getViewState().items,
            cardAdd = { CardAdd(label = "Add new rank", onClick = ::navigateToCompetitiveAdd) },
            cardItem = { competitiveRank ->
                CardWeaponRank(
                    pathToImage = competitiveRank.logo,
                    name = competitiveRank.name,
                    onClick = { navigateToCompetitiveEdit(competitiveRank.id) }
                )
            }
        )
    }
}