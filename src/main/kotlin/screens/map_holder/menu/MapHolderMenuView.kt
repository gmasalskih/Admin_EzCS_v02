package screens.map_holder.menu

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.zIndex
import common_widgets.CardAdd
import common_widgets.CardMapHolder
import common_widgets.ScrollableAddRow
import data.types.ContentSourceType
import org.koin.core.component.inject
import screens.BaseView

class MapHolderMenuView : BaseView<MapHolderMenuController>() {
    override val controller by inject<MapHolderMenuController>()

    @Composable
    override fun setContent(controller: MapHolderMenuController) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            ScrollableAddRow(
                modifier = Modifier.fillMaxWidth().zIndex(2f),
                items = controller.viewState.item.listMapHolder,
                cardAdd = { CardAdd(label = "add map holder", onClick = controller::navigateToMapHolderAdd) },
                cardItem = { mapHolder ->
                    CardMapHolder(
                        background = ContentSourceType.ContentStorageThumbnail(
                            mapHolder.getDocumentName(),
                            mapHolder.wallpaper
                        ),
                        logo = ContentSourceType.ContentStorageOriginal(mapHolder.getDocumentName(), mapHolder.logo),
                        name = mapHolder.name,
                        isCompetitive = mapHolder.isCompetitive,
                        onClick = { controller.navigateToMapHolderEdit(mapHolder.getDocumentName()) }
                    )
                }
            )
        }
    }
}