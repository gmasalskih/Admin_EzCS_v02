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
import common_widgets.CardImageUrl
import common_widgets.TextFieldApp
import ui.spacedBy20dp
import org.koin.core.inject
import screens.BaseView
import ui.greyAccent
import ui.orangeAccent
import utils.toOrderString
import utils.toXPString

class ProfileRankEditView(documentName: String) : BaseView<ProfileRankEditController>() {
    override val controller by inject<ProfileRankEditController>()

    init {
        controller.setDocumentName(documentName)
    }

    @Composable
    override fun setContent(controller: ProfileRankEditController) {
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
                        value = controller.getViewState().item.xp.toXPString(),
                        label = "Change XP",
                        onTextChanged = controller::onXPChange
                    )
                    TextFieldApp(
                        value = controller.getViewState().item.order.toOrderString(),
                        label = "Change order",
                        onTextChanged = controller::onOrderChange
                    )
                }
                CardImageUrl(
                    label = "Change logo",
                    pathToFile = controller.getViewState().item.logo,
                    onClick = controller::onLogoChange
                )
            }
            Row(
                modifier = Modifier.align(Alignment.BottomEnd),
                horizontalArrangement = spacedBy20dp
            ) {
                ButtonApp(
                    label = "delete",
                    color = greyAccent,
                    onClick = controller::onDelete,
                )
                ButtonApp(
                    label = "submit",
                    isActive = controller.getViewState().item.isValid(),
                    color = orangeAccent,
                    onClick = controller::onSubmit,
                )
            }
        }
    }
}
