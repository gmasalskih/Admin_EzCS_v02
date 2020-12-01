package screens.weapons.menu

import androidx.compose.runtime.*
import data.enums.Team
import data.pojo.Weapon
import router.NavigationTargets
import screens.BaseController
import screens.ViewState

class WeaponsMenuController : BaseController<List<Weapon>>() {
    override var state: ViewState<List<Weapon>> by mutableStateOf(
        ViewState(
            title = "Weapons",
            item = listOf(
                Weapon(
                    id = "awp",
                    name = "AWP",
                    teams = listOf(Team.T, Team.CT),
                    image = "https://firebasestorage.googleapis.com/v0/b/ez-cs-f7e97.appspot.com/o/WEAPONS%2Fawp%2Fawp.png?alt=media&token=dd68d59d-d58c-4441-9465-87a29e9d82f5"
                ),
                Weapon(
                    id = "cz75a",
                    name = "CZ75-Auto",
                    teams = listOf(Team.T),
                    image = "https://firebasestorage.googleapis.com/v0/b/ez-cs-f7e97.appspot.com/o/WEAPONS%2Fcz75a%2Fcz75_auto.png?alt=media&token=b3407a12-28ce-4165-9a3d-be922168b74b"
                ),
                Weapon(
                    id = "desert_eagle",
                    name = "desert_eagle",
                    teams = listOf(Team.CT),
                    image = "https://firebasestorage.googleapis.com/v0/b/ez-cs-f7e97.appspot.com/o/WEAPONS%2Fdesert_eagle%2Fdesert_eagle.png?alt=media&token=9c5736c3-dc9e-443d-8f72-bb28f772424b"
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