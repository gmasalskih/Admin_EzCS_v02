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
import ui.spacedBy20dp
import org.koin.core.inject
import screens.BaseView
import ui.greyAccent
import ui.orangeAccent

class ProfileRankAddView : BaseView<ProfileRankAddController>() {
    override val controller by inject<ProfileRankAddController>()

    @Composable
    override fun setContent(controller: ProfileRankAddController) {
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
                        value = controller.getViewState().item.getId(),
                        label = "Enter rank ID",
                    )
                    TextFieldApp(
                        value = controller.getViewState().item.name,
                        label = "Enter rank name",
                        onTextChanged = controller::onNameChange
                    )
                    TextFieldApp(
                        value = controller.getViewState().item.xp,
                        label = "Enter rank XP",
                        onTextChanged = controller::onXPChange
                    )
                }
                CardAddOrImage(
                    label = "add logo",
                    image = controller.getViewState().item.logo,
                    onClick = controller::onLogoAdd
                )
            }
            Row(
                modifier = Modifier.align(Alignment.BottomEnd),
                horizontalArrangement = spacedBy20dp
            ) {
                ButtonApp(
                    label = "clear",
                    color = greyAccent,
                    onClick = controller::onClear
                )
                ButtonApp(
                    label = "submit",
                    color = orangeAccent,
                    onClick = controller::onSubmit
                )
            }
        }
    }
}