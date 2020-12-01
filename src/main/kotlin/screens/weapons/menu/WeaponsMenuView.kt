package screens.weapons.menu

import androidx.compose.runtime.Composable
import common_widgets.CardAdd
import common_widgets.CardWeapon
import common_widgets.ScrollableRowAdd
import org.koin.core.inject
import screens.BaseView

class WeaponsMenuView : BaseView<WeaponsMenuController>() {
    override val controller by inject<WeaponsMenuController>()

    @Composable
    override fun setContent(controller: WeaponsMenuController) {
        ScrollableRowAdd(
            items = controller.getViewState().item,
            cardAdd = { CardAdd(label = "add weapon", onClick = controller::navigateToWeaponsAdd) },
            cardItem = { weapon ->
                CardWeapon(
                    name = weapon.name,
                    image = weapon.image,
                    teams = weapon.teams,
                    onClick = { controller.navigateToWeaponsEdit(weapon.id) }
                )
            }
        )
    }
}