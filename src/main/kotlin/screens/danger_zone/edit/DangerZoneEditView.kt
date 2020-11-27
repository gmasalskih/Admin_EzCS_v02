package screens.danger_zone.edit

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

class DangerZoneEditView(val id: String) : BaseView<DangerZoneEditController>() {
    override val controller by inject<DangerZoneEditController>()

    init {
        setId(id)
    }

    private fun setId(rankId: String) {
        controller.setViewState(
            controller.getViewState().copy(
                id = rankId
            )
        )
    }

    private fun onNameChange(rankName: String) {
        controller.setViewState(
            controller.getViewState().copy(
                name = rankName.toValidName()
            )
        )
    }

    private fun onLogoChange() {
        val newPathToLogo = fileChooser("Select logo", "png") ?: return
        if (!controller.getViewState().pathToLogo.contains(newPathToLogo)) {
            controller.setViewState(
                controller.getViewState().copy(
                    pathToLogo = newPathToLogo
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
                    //Rank ID
                    TextFieldApp(
                        value = controller.getViewState().id,
                        label = "Rank ID",
                    )
                    //Rank name
                    TextFieldApp(
                        value = controller.getViewState().name,
                        label = "Rank name",
                        onTextChanged = ::onNameChange
                    )
                }
                CardAddOrImage(
                    label = "Change logo",
                    pathToImage = controller.getViewState().pathToLogo,
                    onClick = ::onLogoChange
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