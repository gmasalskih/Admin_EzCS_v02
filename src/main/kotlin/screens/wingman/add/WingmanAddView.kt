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
import org.koin.core.inject
import screens.BaseView
import ui.greyAccent
import ui.orangeAccent

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
                        value = controller.getViewState().item.id,
                        label = "Enter rank ID",
                        onTextChanged = controller::onIdChange
                    )
                    TextFieldApp(
                        value = controller.getViewState().item.name,
                        label = "Enter rank name",
                        onTextChanged = controller::onNameChange
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
                    color = orangeAccent,
                    onClick = controller::onSubmit
                )
            }
        }
    }
}