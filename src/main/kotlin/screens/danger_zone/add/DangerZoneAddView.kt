package screens.danger_zone.add

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

class DangerZoneAddView : BaseView<DangerZoneAddController>() {
    override val controller by inject<DangerZoneAddController>()

    private fun onLogoAdd() {
        val newPathToLogo = fileChooser("Select logo", "png") ?: return
        if (!controller.getViewState().pathToLogo.contains(newPathToLogo)) {
            controller.setViewState(
                controller.getViewState().copy(
                    pathToLogo = newPathToLogo
                )
            )
        }
    }

    private fun onNameChange(rankName: String) {
        controller.setViewState(
            controller.getViewState().copy(
                name = rankName.toValidName()
            )
        )
    }

    private fun onIdChange(rankId: String) {
        controller.setViewState(
            controller.getViewState().copy(
                id = rankId.toValidId()
            )
        )
    }

    private fun onSubmit() {
//        TODO("Not yet implemented")
    }

    private fun onClear() {
        controller.clearState()
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