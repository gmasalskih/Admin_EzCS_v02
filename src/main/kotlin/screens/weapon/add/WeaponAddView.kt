package screens.weapon.add

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import common_widgets.*
import org.koin.core.component.inject
import screens.BaseView
import ui.*
import utils.prepareToPrintDataClass

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
                TextFieldApp(
                    value = controller.viewState.item.weaponName,
                    label = "Enter weapon name",
                    onTextChanged = controller::onNameChange
                )
                ScrollableAddRow(
                    modifier = Modifier.fillMaxWidth(),
                    items = controller.getListNameOfBlueprintWeapon(),
                    cardAdd = { CardAdd(label = "Browse file", onClick = controller::onItemsGameFileSelect) },
                    cardItem = { nameOfBlueprintWeapon ->
                        CardBlueprintWeapon(
                            name = nameOfBlueprintWeapon,
                            isSelected = controller.isBlueprintWeaponSelected(nameOfBlueprintWeapon),
                            onClick = { controller.onBlueprintWeaponSelected(nameOfBlueprintWeapon) }
                        )
                    }
                )
                Row(
                    horizontalArrangement = spacedBy20dp
                ) {
                    CardAddOrImage(
                        label = "add logo",
                        pathToFile = controller.viewState.item.logo,
                        onClick = controller::onLogoAdd
                    )
                    CardAddOrImage(
                        label = "add spray",
                        pathToFile = controller.viewState.item.spray,
                        onClick = controller::onSprayAdd
                    )
                    CardAddOrImage(
                        label = "add recoil",
                        pathToFile = controller.viewState.item.recoil,
                        onClick = controller::onRecoilAdd
                    )
                }
                TextApp(
                    text = controller.viewState.item.selectedBlueprintWeapon.second?.let {
                        it.attributes.toString().prepareToPrintDataClass() +
                                it.usedByClasses.toString().prepareToPrintDataClass() +
                                it.visuals.toString().prepareToPrintDataClass()
                    } ?: ""
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