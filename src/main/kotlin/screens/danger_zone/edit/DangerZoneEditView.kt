package screens.danger_zone.edit

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import common_widgets.ButtonApp
import common_widgets.CardImage
import common_widgets.TextFieldApp
import ui.spacedBy20dp
import org.koin.core.component.inject
import screens.BaseView
import ui.greyAccent
import ui.orangeAccent
import utils.toOrderString

class DangerZoneEditView(documentName: String) : BaseView<DangerZoneEditController>() {
    override val controller by inject<DangerZoneEditController>()

    init {
        controller.setDocumentName(documentName)
    }

    @Composable
    override fun setContent(controller: DangerZoneEditController) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            ScrollableColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = spacedBy20dp
            ) {
                TextFieldApp(
                    value = controller.viewState.item.order.toOrderString(),
                    label = "Change order",
                    onTextChanged = controller::onOrderChange
                )
                CardImage(
                    label = "Change logo",
                    pathToFile = controller.viewState.item.logo,
                    onClick = controller::onLogoChange
                )
            }
            Row(
                modifier = Modifier.align(Alignment.BottomEnd),
                horizontalArrangement = spacedBy20dp
            ) {
                ButtonApp(
                    label = "delete",
                    color = greyAccent,
                    onClick = controller::onDelete,
                )
                ButtonApp(
                    label = "submit",
                    isActive = controller.viewState.item.isValid(),
                    color = orangeAccent,
                    onClick = controller::onSubmit,
                )
            }
        }
    }
}