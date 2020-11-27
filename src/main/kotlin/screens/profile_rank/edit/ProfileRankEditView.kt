package screens.profile_rank.edit

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
import ui.orangeAccent
import utils.fileChooser
import utils.toValidName
import utils.toValidXP

class ProfileRankEditView(val id: String) : BaseView<ProfileRankEditController>() {
    override val controller by inject<ProfileRankEditController>()

    init {
        setId(id)
    }

    private fun setId(id: String) {
        controller.setViewState(
            controller.getViewState().copy(
                id = id
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
                        label = "Rank ID",
                        onTextChanged = {}
                    )
                    TextFieldApp(
                        value = controller.getViewState().name,
                        label = "Rank name",
                        onTextChanged = ::onNameChange
                    )
                    TextFieldApp(
                        value = controller.getViewState().xp,
                        label = "Rank XP",
                        onTextChanged = ::onXPChange
                    )
                }
                CardAddOrImage(
                    label = "add logo",
                    pathToImage = controller.getViewState().pathToLogo,
                    onClick = ::onLogoAdd
                )
            }
            ButtonApp(
                modifier = Modifier.align(Alignment.BottomEnd),
                label = "submit",
                color = orangeAccent,
                onClick = ::onSubmit
            )
        }
    }
}