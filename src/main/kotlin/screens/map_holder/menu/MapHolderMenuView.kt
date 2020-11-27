package screens.map_holder.menu

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import common_widgets.CardAdd
import common_widgets.CardMap
import common_widgets.ScrollableRowAdd
import org.koin.core.inject
import screens.BaseView

class MapHolderMenuView : BaseView<MapHolderMenuController>() {
    override val controller by inject<MapHolderMenuController>()

    private fun navigateToMapsAdd() {
        controller.navigateToMapsAdd()
    }

    private fun navigateToMapsEdit(id: String) {
        controller.navigateToMapsEdit(id)
    }

    @Composable
    override fun setContent() {
        ScrollableRowAdd(
            modifier = Modifier.fillMaxWidth(),
            items = 1..3,
            cardAdd = { CardAdd(label = "add map", onClick = ::navigateToMapsAdd) },
            cardItem = {
                CardMap(
                    background = "background/wallpaper.png",
                    logo = "logo/logo.png",
                    name = "Dust II",
                    isCompetitive = true,
                    onClick = { navigateToMapsEdit(it.toString()) }
                )
            }
        )
    }
}