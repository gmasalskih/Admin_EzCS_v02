package screens.weapons.edit

import androidx.compose.runtime.Composable
import common_widgets.ButtonApp
import org.koin.core.inject
import screens.BaseView
import ui.orangeAccent

class WeaponsEditView(val id: String) : BaseView<WeaponsEditController>() {
    override val controller by inject<WeaponsEditController>()

    @Composable
    override fun setContent(controller: WeaponsEditController) {
        ButtonApp(
            label = "test",
            color = orangeAccent,
            onClick = {}
        )
    }
}