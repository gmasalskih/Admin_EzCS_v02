package screens.danger_zone.edit

import androidx.compose.runtime.Composable
import org.koin.core.inject
import screens.BaseView

class DangerZoneEditView(val id: String) : BaseView<DangerZoneEditController>() {
    override val controller by inject<DangerZoneEditController>()

}