package screens.weapon.menu

import androidx.compose.runtime.*
import data.types.TeamType
import data.pojo.Weapon
import router.NavigationTargets
import screens.BaseController
import screens.ViewState

class WeaponMenuController : BaseController<List<Weapon>>() {
    override var state: ViewState<List<Weapon>> by mutableStateOf(
        ViewState(
            title = "Weapons",
            item = listOf(
                Weapon(
                    name = "AWP",
                    teamTypes = listOf(TeamType.T, TeamType.CT),
                    image = ""
                ),
                Weapon(
                    name = "CZ75-Auto",
                    teamTypes = listOf(TeamType.T),
                    image = ""
                ),
                Weapon(
                    name = "desert_eagle",
                    teamTypes = listOf(TeamType.CT),
                    image = ""
                )
            )
        )
    )

    fun navigateToWeaponsAdd() {
        router.navigateTo(NavigationTargets.WeaponsAdd)
    }

    fun navigateToWeaponsEdit(id: String) {
        router.navigateTo(NavigationTargets.WeaponsEdit(id))
    }
}