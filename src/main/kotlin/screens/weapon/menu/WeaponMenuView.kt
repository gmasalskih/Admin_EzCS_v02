package screens.weapon.menu

import androidx.compose.runtime.Composable
import common_widgets.CardAdd
import common_widgets.CardWeapon
import common_widgets.ScrollableAddRow
import data.types.ContentSourceType
import org.koin.core.component.inject
import screens.BaseView

class WeaponMenuView : BaseView<WeaponMenuController>() {
    override val controller by inject<WeaponMenuController>()

    @Composable
    override fun setContent(controller: WeaponMenuController) {
        ScrollableAddRow(
            items = controller.viewState.item.listWeapon,
            cardAdd = { CardAdd(label = "add weapon", onClick = controller::navigateToWeaponsAdd) },
            cardItem = { weapon ->
                CardWeapon(
                    name = weapon.name,
                    image = ContentSourceType.ContentStorageOriginal(weapon.getDocumentName(), weapon.logo),
                    teamTypes = weapon.teamTypes,
                    onClick = { controller.navigateToWeaponsEdit(weapon.getDocumentName()) }
                )
            }
        )
    }
}