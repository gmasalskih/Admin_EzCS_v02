package screens.weapon.edit

import androidx.compose.runtime.Composable
import common_widgets.ButtonApp
import org.koin.core.inject
import screens.BaseView
import ui.orangeAccent

class WeaponEditView(val id: String) : BaseView<WeaponEditController>() {
    override val controller by inject<WeaponEditController>()

    @Composable
    override fun setContent(controller: WeaponEditController) {
        ButtonApp(
            label = "test",
            color = orangeAccent,
            onClick = {}
        )
    }
}