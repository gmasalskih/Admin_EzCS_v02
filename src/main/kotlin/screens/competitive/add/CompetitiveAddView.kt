package screens.competitive.add

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import common_widgets.*
import org.koin.core.inject
import screens.BaseView
import ui.greyAccent
import ui.orangeAccent
import ui.spacedBy20dp
import utils.toOrderString

class CompetitiveAddView : BaseView<CompetitiveAddController>() {
    override val controller by inject<CompetitiveAddController>()

    @Composable
    override fun setContent(controller: CompetitiveAddController) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            ScrollableColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = spacedBy20dp
            ) {
                Row(
                    horizontalArrangement = spacedBy20dp
                ) {
                    TextFieldApp(
                        value = controller.getViewState().item.name,
                        label = "Enter rank name",
                        onTextChanged = controller::onNameChange
                    )
                    TextFieldApp(
                        value = controller.getViewState().item.order.toOrderString(),
                        label = "Enter order",
                        onTextChanged = controller::onOrderChange
                    )
                }
                CardAddOrImage(
                    label = "add logo",
                    image = controller.getViewState().item.logo,
                    onClick = controller::onLogoAdd
                )
            }
            Row(
                modifier = Modifier.align(Alignment.BottomEnd),
                horizontalArrangement = spacedBy20dp
            ) {
                ButtonApp(
                    label = "clear",
                    color = greyAccent,
                    onClick = controller::onClear
                )
                ButtonApp(
                    label = "submit",
                    isActive = controller.getViewState().item.isValid(),
                    color = orangeAccent,
                    onClick = controller::onSubmit
                )
            }
        }
    }
}