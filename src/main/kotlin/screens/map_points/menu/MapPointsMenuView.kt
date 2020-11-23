package screens.map_points.menu

import androidx.compose.foundation.ScrollableRow
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import common_widgets.CardAdd
import common_widgets.CardMap
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

    @Composable
    override fun render() = renderContent {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            CardAdd(title = "add map point", click = ::navigateToAddMapPoint)
            ScrollableRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                repeat(10) {
                    CardMap(
                        background = "background/wallpaper.png",
                        logo = "logo/logo.png",
                        name = "Dust II",
                        isCompetitive = true,
                        onClick = { navigateToMap(it.toString()) }
                    )
                }
            }
        }
    }
}