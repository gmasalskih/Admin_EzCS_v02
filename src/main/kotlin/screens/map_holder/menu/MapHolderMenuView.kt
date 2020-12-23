package screens.map_holder.menu

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.zIndex
import common_widgets.CardAdd
import common_widgets.CardMapHolder
import common_widgets.ScrollableRowAdd
import org.koin.core.inject
import screens.BaseView

class MapHolderMenuView : BaseView<MapHolderMenuController>() {
    override val controller by inject<MapHolderMenuController>()

    @Composable
    override fun setContent(controller: MapHolderMenuController) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            ScrollableRowAdd(
                modifier = Modifier.fillMaxWidth().zIndex(2f),
                items = controller.getViewState().item,
                cardAdd = { CardAdd(label = "add map holder", onClick = controller::navigateToMapHolderAdd) },
                cardItem = { menuState ->
                    CardMapHolder(
                        background = menuState.wallpaper,
                        logo = menuState.logo,
                        name = menuState.name,
                        isCompetitive = menuState.isCompetitive,
                        onClick = { controller.navigateToMapHolderEdit(menuState.documentName) }
                    )
                }
            )
        }
    }
}