package screens.map_points.add

import androidx.compose.runtime.Composable
import org.koin.core.inject
import screens.BaseView

class MapPointsAddView : BaseView<MapPointsAddController>() {
    override val controller by inject<MapPointsAddController>()

    @Composable
    override fun render() = renderContent {

    }
}