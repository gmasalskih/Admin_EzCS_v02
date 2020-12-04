package screens.weapon.add

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import common_widgets.ButtonApp
import common_widgets.CardAddOrImage
import common_widgets.TextFieldApp
import org.koin.core.inject
import screens.BaseView
import ui.*
import utils.toValidId

class WeaponAddView : BaseView<WeaponAddController>() {
    override val controller by inject<WeaponAddController>()

    @Composable
    override fun setContent(controller: WeaponAddController) {
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
                    TextFieldApp(
                        value = controller.getViewState().item.name.toValidId(),
                        label = "Enter weapon ID",
                    )
                    TextFieldApp(
                        value = controller.getViewState().item.name,
                        label = "Enter weapon name",
                        onTextChanged = controller::onNameChange
                    )
                }
                Row(
                    horizontalArrangement = spacedBy20dp
                ) {
                    CardAddOrImage(
                        label = "add image",
                        image = controller.getViewState().item.image,
                        onClick = controller::onImageAdd
                    )
                    CardAddOrImage(
                        label = "add spray",
                        image = controller.getViewState().item.spray,
                        onClick = controller::onSprayAdd
                    )
                    CardAddOrImage(
                        label = "add recoil",
                        image = controller.getViewState().item.recoil,
                        onClick = controller::onRecoilAdd
                    )
                }
                Row(
                    horizontalArrangement = spacedBy20dp,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ButtonApp(
                        label = "browse",
                        color = orangeAccent,
                        onClick = controller::onBrowse
                    )
                    Text(
                        text = controller.configFile,
                        fontSize = fontSize14sp,
                        fontFamily = verdanaRegular,
                        color = greyAccent
                    )
                }
                if (controller.configFile.isNotBlank())
                    ButtonApp(
                        label = "parse",
                        color = orangeAccent,
                        onClick = controller::onParse
                    )
                Text(
                    text = "parse text",
                    fontFamily = verdanaRegular,
                    fontSize = fontSize14sp,
                    color = greyAccent
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