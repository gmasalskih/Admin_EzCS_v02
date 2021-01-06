package screens.weapon.edit

import androidx.compose.runtime.Composable
import common_widgets.ButtonApp
import org.koin.core.component.inject
import screens.BaseView
import ui.orangeAccent

class WeaponEditView(documentName: String) : BaseView<WeaponEditController>() {
    override val controller by inject<WeaponEditController>()

    init {
        controller.setDocumentName(documentName)
    }

    @Composable
    override fun setContent(controller: WeaponEditController) {
        ButtonApp(
            label = "test",
            color = orangeAccent,
            onClick = {}
        )
    }
}