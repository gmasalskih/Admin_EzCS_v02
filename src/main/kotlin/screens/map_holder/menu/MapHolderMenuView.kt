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

    @Composable
    override fun setContent(controller: MapHolderMenuController) {
        ScrollableRowAdd(
            modifier = Modifier.fillMaxWidth(),
            items = controller.getViewState().item,
            cardAdd = { CardAdd(label = "add map", onClick = controller::navigateToMapHolderAdd) },
            cardItem = { mapHolder ->
                CardMap(
                    background = mapHolder.wallpaper,
                    logo = mapHolder.logo,
                    name = mapHolder.name,
                    isCompetitive = true,
                    onClick = { controller.navigateToMapHolderEdit(mapHolder.id) }
                )
            }
        )
    }
}