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

    private fun onIdChange(id: String) {
        controller.setViewState(
            controller.getViewState().copy(
                id = id.toValidId()
            )
        )
    }

    private fun onNameChange(name: String) {
        controller.setViewState(
            controller.getViewState().copy(
                name = name.toValidName()
            )
        )
    }

    private fun onXPChange(xp: String) {
        controller.setViewState(
            controller.getViewState().copy(
                xp = xp.toValidXP()
            )
        )
    }

    private fun onLogoAdd() {
        val pathToLogo = fileChooser("Select logo", "png") ?: return
        if (!controller.getViewState().pathToLogo.contains(pathToLogo)) {
            controller.setViewState(
                controller.getViewState().copy(
                    pathToLogo = pathToLogo
                )
            )
        }
    }

    private fun onClear() {
        controller.clearState()
    }

    private fun onSubmit() {
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
                        value = controller.getViewState().id,
                        label = "Enter rank ID",
                        onTextChanged = ::onIdChange
                    )
                    TextFieldApp(
                        value = controller.getViewState().name,
                        label = "Enter rank name",
                        onTextChanged = ::onNameChange
                    )
                    TextFieldApp(
                        value = controller.getViewState().xp,
                        label = "Enter rank XP",
                        onTextChanged = ::onXPChange
                    )
                }
                CardAddOrImage(
                    label = "add logo",
                    pathToImage = controller.getViewState().pathToLogo,
                    onClick = ::onLogoAdd
                )
            }
            Row(
                modifier = Modifier.align(Alignment.BottomEnd),
                horizontalArrangement = spacedBy20dp
            ) {
                ButtonApp(
                    label = "clear",
                    color = greyAccent,
                    onClick = ::onClear
                )
                ButtonApp(
                    label = "submit",
                    color = orangeAccent,
                    onClick = ::onSubmit
                )
            }
        }
    }
}