package screens.competitive.edit

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import common_widgets.*
import org.koin.core.component.inject
import screens.BaseView
import ui.greyAccent
import ui.orangeAccent
import ui.spacedBy20dp
import utils.toOrderString

class CompetitiveEditView(documentName: String) : BaseView<CompetitiveEditController>() {
    override val controller by inject<CompetitiveEditController>()

    init {
        controller.setDocumentName(documentName)
    }

    @Composable
    override fun setContent(controller: CompetitiveEditController) {
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
                    color = orangeAccent,
                    isActive = controller.viewState.item.isValid(),
                    onClick = controller::onSubmit,
                )
            }
        }
    }
}