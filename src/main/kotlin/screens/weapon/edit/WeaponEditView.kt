package screens.weapon.edit

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import common_widgets.*
import org.koin.core.component.inject
import screens.BaseView
import ui.greyAccent
import ui.orangeAccent
import ui.spacedBy20dp
import utils.prepareToPrintDataClass

class WeaponEditView(documentName: String) : BaseView<WeaponEditController>() {
    override val controller by inject<WeaponEditController>()

    init {
        controller.setDocumentName(documentName)
    }

    @Composable
    override fun setContent(controller: WeaponEditController) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            ScrollableColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = spacedBy20dp,
            ) {
                Row(
                    horizontalArrangement = spacedBy20dp
                ) {
                    CardImage(
                        label = "add logo",
                        pathToFile = controller.viewState.item.logo,
                        onClick = controller::onLogoChange
                    )
                    CardImage(
                        label = "add spray",
                        pathToFile = controller.viewState.item.spray,
                        onClick = controller::onSprayChange
                    )
                    CardImage(
                        label = "add recoil",
                        pathToFile = controller.viewState.item.recoil,
                        onClick = controller::onRecoilChange
                    )
                }
                TextApp(
                    text = controller.viewState.item.toString().prepareToPrintDataClass()
                )
            }
            Row(
                modifier = Modifier.align(Alignment.BottomEnd),
                horizontalArrangement = spacedBy20dp
            ) {
                ButtonApp(
                    label = "delete",
                    color = greyAccent,
                    onClick = controller::onDelete
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