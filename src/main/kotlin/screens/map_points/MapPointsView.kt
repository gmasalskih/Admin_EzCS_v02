package screens.map_points

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import org.koin.core.inject
import screens.BaseView

class MapPointsView : BaseView<MapPointsViewState, MapPointsController>() {
    override val controller: MapPointsController by inject()

    @Composable
    override fun renderContent() {
        Text("MapPointsView")
    }
}