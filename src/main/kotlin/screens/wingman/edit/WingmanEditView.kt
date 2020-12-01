package screens.wingman.edit

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
import ui.orangeAccent

class WingmanEditView(val id: String) : BaseView<WingmanEditController>() {
    override val controller by inject<WingmanEditController>()

    init {
        controller.setId(id)
    }

    @Composable
    override fun setContent(controller: WingmanEditController) {
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
                    //Rank ID
                    TextFieldApp(
                        value = controller.getViewState().item.id,
                        label = "Rank ID",
                    )
                    //Rank name
                    TextFieldApp(
                        value = controller.getViewState().item.name,
                        label = "Rank name",
                        onTextChanged = controller::onNameChange
                    )
                }
                CardAddOrImage(
                    label = "Change logo",
                    image = controller.getViewState().item.logo,
                    onClick = controller::onLogoChange
                )
            }
            ButtonApp(
                modifier = Modifier.align(Alignment.BottomEnd),
                label = "submit",
                color = orangeAccent,
                onClick = controller::onSubmit
            )
        }
    }
}