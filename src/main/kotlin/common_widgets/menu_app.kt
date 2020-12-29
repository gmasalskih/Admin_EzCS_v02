package common_widgets

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import router.NavigationTargets
import router.Router
import ui.dark
import ui.elevation6dp

private val buttonsMenuApp = listOf(
    ButtonsMenuApp.Maps,
    ButtonsMenuApp.MapPoints,
    ButtonsMenuApp.Weapons,
    ButtonsMenuApp.Competitive,
    ButtonsMenuApp.Wingman,
    ButtonsMenuApp.DangerZone,
    ButtonsMenuApp.ProfileRank
)

private sealed class ButtonsMenuApp(val title: String, val logoPath: String, val navigationTarget: NavigationTargets) {
    object Maps : ButtonsMenuApp(
        title = "Map holder",
        logoPath = "icons/icon_map_holder.png",
        navigationTarget = NavigationTargets.MapHolderMenu
    )

    object MapPoints : ButtonsMenuApp(
        title = "Map Point",
        logoPath = "icons/icon_map_point.png",
        navigationTarget = NavigationTargets.MapPointsMenu
    )

    object Weapons : ButtonsMenuApp(
        title = "Weapon",
        logoPath = "icons/icon_weapon.png",
        navigationTarget = NavigationTargets.WeaponsMenu
    )

    object Competitive : ButtonsMenuApp(
        title = "Competitive",
        logoPath = "icons/icon_competitive.png",
        navigationTarget = NavigationTargets.CompetitiveMenu
    )

    object Wingman : ButtonsMenuApp(
        title = "Wingman",
        logoPath = "icons/icon_wingman.png",
        navigationTarget = NavigationTargets.WingmanMenu
    )

    object DangerZone : ButtonsMenuApp(
        title = "Danger Zone",
        logoPath = "icons/icon_danger_zone.png",
        navigationTarget = NavigationTargets.DangerZoneMenu
    )

    object ProfileRank : ButtonsMenuApp(
        title = "Profile Rank",
        logoPath = "icons/icon_profile_rank.png",
        navigationTarget = NavigationTargets.ProfileRankMenu
    )
}

@Composable
fun MenuApp(router: Router) {
    var stateButtonsMenuApp: ButtonsMenuApp by remember { mutableStateOf(ButtonsMenuApp.Maps) }
    Box(
        modifier = Modifier.fillMaxHeight().preferredWidth(180.dp),
    ) {

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = dark,
            elevation = elevation6dp
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                LogoApp()
                ScrollableColumn(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    buttonsMenuApp.map {
                        ButtonMenu(
                            isActive = stateButtonsMenuApp == it,
                            title = it.title,
                            logoPath = it.logoPath
                        ) {
                            stateButtonsMenuApp = it
                            router.navigateTo(it.navigationTarget, true)
                        }
                    }
                }
            }
        }
    }
}