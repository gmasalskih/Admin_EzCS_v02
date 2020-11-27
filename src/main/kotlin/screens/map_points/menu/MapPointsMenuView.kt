package screens.map_points.menu

import androidx.compose.runtime.Composable
import common_widgets.CardAdd
import common_widgets.CardMap
import common_widgets.ScrollableRowAdd
import org.koin.core.inject
import screens.BaseView

class MapPointsMenuView : BaseView<MapPointsMenuController>() {
    override val controller by inject<MapPointsMenuController>()

    private fun navigateToMapPointsAdd() {
        controller.navigateToMapPointsAdd()
    }

    private fun navigateToMapPointsEdit(id: String) {
        controller.navigateToMapPointsEdit(id)
    }

    @Composable
    override fun setContent() {
        ScrollableRowAdd(
            items = 1..6,
            cardAdd = { CardAdd(label = "add map point", onClick = ::navigateToMapPointsAdd) },
            cardItem = {
                CardMap(
                    background = "background/wallpaper.png",
                    logo = "logo/logo.png",
                    name = "$it",
                    isCompetitive = true,
                    onClick = { navigateToMapPointsEdit(it.toString()) }
                )
            }
        )
    }
}