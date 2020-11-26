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
import utils.fileChooser
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

    private fun onChangeLogo() {
        val newPathToLogo = fileChooser("Select logo", "png") ?: return
        if (!controller.getViewState().pathToLogo.contains(newPathToLogo)) {
            controller.setViewState(
                controller.getViewState().copy(
                    pathToLogo = newPathToLogo
                )
            )
        }
    }

    private fun submitBtnClick() {
//        TODO("Not yet implemented")
    }

    @Composable
    override fun setContent() {
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
                CardAddOrImage(
                    label = "Change logo",
                    pathToImage = controller.getViewState().pathToLogo,
                    onClick = ::onChangeLogo
                )
            }
            ButtonApp(
                modifier = Modifier.align(Alignment.BottomEnd),
                label = "submit",
                color = orangeAccent,
                onClick = ::submitBtnClick
            )
        }
    }
}