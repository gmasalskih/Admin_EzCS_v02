package screens.danger_zone.menu

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import common_widgets.CardAdd
import common_widgets.CardRank
import common_widgets.ScrollableAddRow
import data.types.ContentSourceType
import org.koin.core.inject
import screens.BaseView

class DangerZoneMenuView : BaseView<DangerZoneMenuController>() {
    override val controller by inject<DangerZoneMenuController>()

    @Composable
    override fun setContent(controller: DangerZoneMenuController) {
        ScrollableAddRow(
            modifier = Modifier.fillMaxWidth(),
            items = controller.getViewState().item.listDangerZone,
            cardAdd = { CardAdd(label = "Add new rank", onClick = controller::navigateToDangerZoneAdd) },
            cardItem = { dangerZone ->
                CardRank(
                    pathToImage = ContentSourceType.ContentStorageOriginal(
                        dangerZone.getDocumentName(),
                        dangerZone.logo
                    ),
                    name = dangerZone.name,
                    onClick = { controller.navigateToDangerZoneEdit(dangerZone.getDocumentName()) }
                )
            }
        )
    }
}