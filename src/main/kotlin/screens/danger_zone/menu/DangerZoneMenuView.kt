package screens.danger_zone.menu

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import common_widgets.CardAdd
import common_widgets.CardWeaponRank
import common_widgets.ScrollableRowAdd
import org.koin.core.inject
import screens.BaseView

class DangerZoneMenuView : BaseView<DangerZoneMenuController>() {
    override val controller by inject<DangerZoneMenuController>()

    private fun navigateToDangerZoneAdd() {
        controller.navigateToDangerZoneAdd()
    }

    private fun navigateToDangerZoneEdit(id: String) {
        controller.navigateToDangerZoneEdit(id)
    }

    @Composable
    override fun setContent() {
        ScrollableRowAdd(
            modifier = Modifier.fillMaxWidth(),
            items = controller.getViewState().items,
            cardAdd = { CardAdd(label = "Add new rank", onClick = ::navigateToDangerZoneAdd) },
            cardItem = { item ->
                CardWeaponRank(
                    pathToImage = item.logo,
                    name = item.name,
                    onClick = { navigateToDangerZoneEdit(item.id) }
                )
            }
        )
    }
}