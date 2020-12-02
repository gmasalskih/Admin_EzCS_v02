package screens.map_holder.menu

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.zIndex
import common_widgets.CardAdd
import common_widgets.CardMap
import common_widgets.ScrollableRowAdd
import org.koin.core.inject
import screens.BaseView
import screens.TypeScreenState
import ui.*

class MapHolderMenuView : BaseView<MapHolderMenuController>() {
    override val controller by inject<MapHolderMenuController>()

    @Composable
    override fun setContent(controller: MapHolderMenuController) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            if (controller.getViewState().typeScreenState is TypeScreenState.Loading) {

            }
            ScrollableRowAdd(
                modifier = Modifier.fillMaxWidth().zIndex(2f),
                items = controller.getViewState().item,
                cardAdd = { CardAdd(label = "add map", onClick = controller::navigateToMapHolderAdd) },
                cardItem = { mapHolder ->
                    CardMap(
                        background = mapHolder.wallpaper,
                        logo = mapHolder.logo,
                        name = mapHolder.name,
                        isCompetitive = mapHolder.isCompetitive,
                        onClick = { controller.navigateToMapHolderEdit(mapHolder.id) }
                    )
                }
            )
        }
    }
}