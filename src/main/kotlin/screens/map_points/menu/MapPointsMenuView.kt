package screens.map_points.menu

import common_widgets.CardAdd
import common_widgets.CardMap
import common_widgets.ScrollableRowAdd
import org.koin.core.inject
import screens.BaseView

class MapPointsMenuView : BaseView<MapPointsMenuController>() {
    override val controller by inject<MapPointsMenuController>()

    private fun navigateToAddMapPoint() {
        //TODO("Not yet implemented")
    }

    private fun navigateToMap(mapID: String) {
        //TODO("Not yet implemented")
    }

    override fun onViewCreate() {
        super.onViewCreate()
        setContent {
            ScrollableRowAdd(
                items = 1..6,
                cardAdd = { CardAdd(label = "add map point", onClick = ::navigateToAddMapPoint) },
                cardItem = {
                    CardMap(
                        background = "background/wallpaper.png",
                        logo = "logo/logo.png",
                        name = "$it",
                        isCompetitive = true,
                        onClick = { navigateToMap(it.toString()) }
                    )
                },
            )
        }
    }
}