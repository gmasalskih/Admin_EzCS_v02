package screens.map_points.menu

import androidx.compose.runtime.Composable
import common_widgets.CardAdd
import common_widgets.CardMap
import common_widgets.ScrollableRowAdd
import org.koin.core.inject
import screens.BaseView

class MapPointsMenuView : BaseView<MapPointsMenuController>() {
    override val controller by inject<MapPointsMenuController>()

    @Composable
    override fun setContent(controller: MapPointsMenuController) {
        ScrollableRowAdd(
            items = controller.getViewState().item,
            cardAdd = { CardAdd(label = "add map point", onClick = controller::navigateToMapPointsAdd) },
            cardItem = { mapHolder ->
                CardMap(
                    background = mapHolder.wallpaper,
                    logo = mapHolder.logo,
                    name = mapHolder.name,
                    isCompetitive = mapHolder.isCompetitive,
                    onClick = { controller.navigateToMapPointsEdit(mapHolder.id) }
                )
            }
        )
    }
}