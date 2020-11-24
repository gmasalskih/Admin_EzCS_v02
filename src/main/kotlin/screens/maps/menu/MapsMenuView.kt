package screens.maps.menu

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import common_widgets.CardAdd
import common_widgets.CardMap
import common_widgets.ScrollableRowAdd
import org.koin.core.inject
import screens.BaseView
import utils.ViewComponent

class MapsMenuView : BaseView<MapsMenuController>() {
    override val controller by inject<MapsMenuController>()

    private fun navigateToAddMap() {
        controller.navigateToAddMap()
    }

    private fun navigateToMap(mapId: String) {
        controller.navigateToEditMap(mapId)
    }

    override fun onViewCreate() {
        super.onViewCreate()
        setContent {
            ScrollableRowAdd(
                modifier = Modifier.fillMaxWidth(),
                items = 1..3,
                cardAdd = { CardAdd(label = "add map", onClick = ::navigateToAddMap) },
                cardItem = {
                    CardMap(
                        background = "background/wallpaper.png",
                        logo = "logo/logo.png",
                        name = "Dust II",
                        isCompetitive = true,
                        onClick = { navigateToMap(it.toString()) }
                    )
                }
            )
        }
    }
}