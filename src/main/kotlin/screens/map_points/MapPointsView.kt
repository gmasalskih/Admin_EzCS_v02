package screens.map_points

import androidx.compose.foundation.ScrollableRow
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import common_widgets.AddCard
import common_widgets.MapCard
import org.koin.core.inject
import screens.BaseView

class MapPointsView : BaseView<MapPointsController>() {
    override val controller: MapPointsController by inject()

    private fun navigateToAddMapPoint() {

    }

    private fun navigateToMap(mapID: String) {

    }

    @Composable
    override fun render() = renderContent {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            AddCard(title = "add map point", click = ::navigateToAddMapPoint)
            ScrollableRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                repeat(10) {
                    MapCard(
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