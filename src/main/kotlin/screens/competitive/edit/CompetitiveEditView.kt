package screens.competitive.edit

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import common_widgets.*
import org.koin.core.inject
import screens.BaseView
import ui.orangeAccent
import ui.spacedBy20dp

class CompetitiveEditView(val id: String) : BaseView<CompetitiveEditController>() {
    override val controller by inject<CompetitiveEditController>()

    init {
//        controller.setId(id)
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
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = spacedBy20dp
                ) {
                    //Rank ID
                    TextFieldApp(
                        value = controller.getViewState().item.getId(),
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