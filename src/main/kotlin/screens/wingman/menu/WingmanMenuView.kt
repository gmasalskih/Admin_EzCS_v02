package screens.wingman.menu

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import common_widgets.CardAdd
import common_widgets.CardRank
import common_widgets.ScrollableRowAdd
import org.koin.core.inject
import screens.BaseView

class WingmanMenuView : BaseView<WingmanMenuController>() {
    override val controller by inject<WingmanMenuController>()

    @Composable
    override fun setContent(controller: WingmanMenuController) {
        ScrollableRowAdd(
            modifier = Modifier.fillMaxWidth(),
            items = controller.getViewState().item,
            cardAdd = { CardAdd(label = "Add new rank", onClick = controller::navigateToWingmanAdd) },
            cardItem = { wingman ->
                CardRank(
                    pathToImage = wingman.logo,
                    name = wingman.name,
                    onClick = { controller.navigateToWingmanEdit(wingman.id()) }
                )
            }
        )
    }
}