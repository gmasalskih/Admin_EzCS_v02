package screens.weapon.menu

import androidx.compose.runtime.Composable
import common_widgets.CardAdd
import common_widgets.CardWeapon
import common_widgets.ScrollableRowAdd
import org.koin.core.inject
import screens.BaseView

class WeaponMenuView : BaseView<WeaponMenuController>() {
    override val controller by inject<WeaponMenuController>()

    @Composable
    override fun setContent(controller: WeaponMenuController) {
        ScrollableRowAdd(
            items = controller.getViewState().item,
            cardAdd = { CardAdd(label = "add weapon", onClick = controller::navigateToWeaponsAdd) },
            cardItem = { weapon ->
                CardWeapon(
                    name = weapon.name,
                    image = weapon.image,
                    teamTypes = weapon.teamTypes,
                    onClick = { controller.navigateToWeaponsEdit(weapon.id()) }
                )
            }
        )
    }
}