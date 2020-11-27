package screens.wingman.menu

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import common_widgets.CardAdd
import common_widgets.CardWeaponRank
import common_widgets.ScrollableRowAdd
import org.koin.core.inject
import screens.BaseView

class WingmanMenuView : BaseView<WingmanMenuController>() {
    override val controller by inject<WingmanMenuController>()

    private fun onAddNewRank() {
        controller.navigateToAddWingmanRank()
    }

    private fun onEditRank(id: String) {
        controller.navigateToEditWingmanRankRank(id)
    }

    @Composable
    override fun setContent() {
        ScrollableRowAdd(
            modifier = Modifier.fillMaxWidth(),
            items = controller.getViewState().items,
            cardAdd = { CardAdd(label = "Add new rank", onClick = ::onAddNewRank) },
            cardItem = { wingmanRank ->
                CardWeaponRank(
                    pathToImage = wingmanRank.logo,
                    name = wingmanRank.name,
                    onClick = { onEditRank(wingmanRank.id) }
                )
            }
        )
    }
}