package screens.danger_zone.add

import androidx.compose.runtime.Composable
import org.koin.core.inject
import screens.BaseView

class DangerZoneAddView : BaseView<DangerZoneAddController>() {
    override val controller by inject<DangerZoneAddController>()

    @Composable
    override fun render() = renderContent {

    }
}