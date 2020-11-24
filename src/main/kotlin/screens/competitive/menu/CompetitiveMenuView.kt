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

    private fun onAddNewRank() {
        controller.navigateToAddCompetitiveRank()
    }

    private fun onEditRank(id: String) {
        controller.navigateToEditCompetitiveRank(id)
    }

    override fun onViewCreate() {
        super.onViewCreate()
        setContent {
            ScrollableRowAdd(
                modifier = Modifier.fillMaxWidth(),
                items = 1..6,
                cardAdd = { CardAdd(label = "Add new rank", onClick = ::onAddNewRank) },
                cardItem = {
                    CardWeaponRank(
                        pathToImage = "assets/01_s1.png",
                        name = "Silver 1",
                        onClick = { onEditRank(it.toString()) }
                    )
                }
            )
        }
    }
}