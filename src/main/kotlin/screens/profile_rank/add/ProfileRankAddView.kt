package screens.profile_rank.add

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
import common_widgets.spacedBy20dp
import org.koin.core.inject
import screens.BaseView
import ui.greyAccent
import ui.orangeAccent
import utils.fileChooser
import utils.toValidId
import utils.toValidName
import utils.toValidXP

class ProfileRankAddView : BaseView<ProfileRankAddController>() {
    override val controller by inject<ProfileRankAddController>()

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

    private fun onRankXPChanged(rankXP: String) {
        controller.setViewState(
            controller.getViewState().copy(
                rankXP = rankXP.toValidXP()
            )
        )
    }

    private fun onAddLogo() {
        val newPathToLogo = fileChooser("Select logo", "png") ?: return
        if (!controller.getViewState().pathToLogo.contains(newPathToLogo)) {
            controller.setViewState(
                controller.getViewState().copy(
                    pathToLogo = newPathToLogo
                )
            )
        }
    }

    private fun clearBtnClick() {
        controller.clearState()
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
                    TextFieldApp(
                        value = controller.getViewState().rankXP,
                        label = "Enter rank XP",
                        onTextChanged = ::onRankXPChanged
                    )
                }
                CardAddOrImage(
                    label = "add logo",
                    pathToImage = controller.getViewState().pathToLogo,
                    onClick = ::onAddLogo
                )
            }
            Row(
                modifier = Modifier.align(Alignment.BottomEnd),
                horizontalArrangement = spacedBy20dp
            ) {
                ButtonApp(
                    label = "clear",
                    color = greyAccent,
                    onClick = ::clearBtnClick
                )
                ButtonApp(
                    label = "submit",
                    color = orangeAccent,
                    onClick = ::submitBtnClick
                )
            }
        }
    }
}