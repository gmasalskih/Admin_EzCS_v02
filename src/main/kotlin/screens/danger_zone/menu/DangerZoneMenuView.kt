package screens.danger_zone.menu

import androidx.compose.runtime.Composable
import org.koin.core.inject
import screens.BaseView

class DangerZoneMenuView : BaseView<DangerZoneMenuController>() {
    override val controller by inject<DangerZoneMenuController>()
}