package screens.wingman.add

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import common_widgets.ButtonApp
import common_widgets.CardAddOrImage
import common_widgets.TextFieldApp
import ui.spacedBy20dp
import org.koin.core.component.inject
import screens.BaseView
import ui.greyAccent
import ui.orangeAccent
import utils.toOrderString

class WingmanAddView : BaseView<WingmanAddController>() {
    override val controller by inject<WingmanAddController>()

    @Composable
    override fun setContent(controller: WingmanAddController) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            ScrollableColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = spacedBy20dp
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = spacedBy20dp

                ) {
                    TextFieldApp(
                        value = controller.viewState.item.name,
                        label = "Enter rank name",
                        onTextChanged = controller::onNameChange
                    )
                    TextFieldApp(
                        value = controller.viewState.item.order.toOrderString(),
                        label = "Enter order",
                        onTextChanged = controller::onOrderChange
                    )
                }
                CardAddOrImage(
                    label = "add logo",
                    pathToFile = controller.viewState.item.logo,
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
                    isActive = controller.viewState.item.isValid(),
                    color = orangeAccent,
                    onClick = controller::onSubmit
                )
            }
        }
    }
}