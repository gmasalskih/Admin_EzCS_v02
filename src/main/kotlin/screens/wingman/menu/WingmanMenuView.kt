package screens.wingman.menu

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import common_widgets.CardAdd
import common_widgets.CardRank
import common_widgets.ScrollableAddRow
import data.types.ContentSourceType
import org.koin.core.component.inject
import screens.BaseView

class WingmanMenuView : BaseView<WingmanMenuController>() {
    override val controller by inject<WingmanMenuController>()

    @Composable
    override fun setContent(controller: WingmanMenuController) {
        ScrollableAddRow(
            modifier = Modifier.fillMaxWidth(),
            items = controller.viewState.item.listWingman,
            cardAdd = { CardAdd(label = "Add new rank", onClick = controller::navigateToWingmanAdd) },
            cardItem = { wingman ->
                CardRank(
                    pathToImage = ContentSourceType.ContentStorageOriginal(wingman.getDocumentName(), wingman.logo),
                    name = wingman.name,
                    onClick = { controller.navigateToWingmanEdit(wingman.getDocumentName()) }
                )
            }
        )
    }
}