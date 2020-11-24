package screens.competitive.add

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import common_widgets.CardAdd
import common_widgets.TextFieldApp
import common_widgets.spacedBy20dp
import org.koin.core.inject
import screens.BaseView
import utils.toValidId
import utils.toValidName

class CompetitiveAddView : BaseView<CompetitiveAddController>() {
    override val controller by inject<CompetitiveAddController>()

    private fun onRankIdChanged(rankId: String) {
        controller.setViewState(
            controller.getViewState().copy(
                rankId = rankId.toValidId()
            )
        )
    }

    private fun onRankNameChanged(rankName: String) {
        controller.setViewState(
            controller.getViewState().copy(
                rankName = rankName.toValidName()
            )
        )
    }

    private fun onAddLogo() {

    }

    override fun onViewCreate() {
        super.onViewCreate()
        setContent {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = spacedBy20dp
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = spacedBy20dp

                ) {
                    TextFieldApp(
                        value = controller.getViewState().rankId,
                        label = "Enter rank ID",
                        onTextChanged = ::onRankIdChanged
                    )
                    TextFieldApp(
                        value = controller.getViewState().rankName,
                        label = "Enter rank name",
                        onTextChanged = ::onRankNameChanged
                    )
                }
                CardAdd(
                    label = "add logo",
                    onClick = ::onAddLogo
                )
            }
        }
    }
}