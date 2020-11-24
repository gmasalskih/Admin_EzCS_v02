package screens.map_points.edit

import androidx.compose.runtime.Composable
import org.koin.core.inject
import screens.BaseView

class MapPointsEditView(val id: String) : BaseView<MapPointsEditController>() {
    override val controller by inject<MapPointsEditController>()

    @Composable
    override fun setContent() {
//        TODO("Not yet implemented")
    }
}