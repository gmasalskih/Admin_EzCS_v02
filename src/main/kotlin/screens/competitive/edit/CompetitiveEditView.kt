package screens.competitive.edit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import common_widgets.TextFieldApp
import common_widgets.spacedBy20dp
import org.koin.core.inject
import screens.BaseView
import utils.toValidName

class CompetitiveEditView(val id: String) : BaseView<CompetitiveEditController>() {
    override val controller by inject<CompetitiveEditController>()

    init {
        setRankId(id)
    }

    private fun setRankId(rankId: String) {
        controller.setViewState(
            controller.getViewState().copy(
                rankId = rankId
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
                    //Rank ID
                    TextFieldApp(
                        value = controller.getViewState().rankId,
                        label = "Rank ID",
                    )
                    //Rank name
                    TextFieldApp(
                        value = controller.getViewState().rankName,
                        label = "Rank name",
                        onTextChanged = ::onRankNameChanged
                    )
                }
            }
        }
    }
}