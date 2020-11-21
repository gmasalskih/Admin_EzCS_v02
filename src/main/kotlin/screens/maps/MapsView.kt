package screens.maps

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

class MapsView : BaseView<MapsController>() {
    override val controller: MapsController by inject()

    private fun navigateToAddMap() {
        controller.navigateToAddMap()
    }

    private fun navigateToMap(mapId: String) {

    }

    @Composable
    override fun render() = renderContent {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            AddCard(title = "add map", click = ::navigateToAddMap)
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