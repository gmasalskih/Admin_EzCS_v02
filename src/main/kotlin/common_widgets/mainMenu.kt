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

private val menuButtons = listOf(
    MenuButtons.Maps,
    MenuButtons.MapPoints,
    MenuButtons.Weapons,
    MenuButtons.Competitive,
    MenuButtons.Wingman,
    MenuButtons.DangerZone,
    MenuButtons.ProfileRank
)

private sealed class MenuButtons(val title: String, val logoPath: String, val navigationTarget: NavigationTargets) {
    object Maps : MenuButtons(
        title = "Maps",
        logoPath = "icons/icon_maps.png",
        navigationTarget = NavigationTargets.Maps
    )

    object MapPoints : MenuButtons(
        title = "Map Points",
        logoPath = "icons/icon_map_points.png",
        navigationTarget = NavigationTargets.MapPoints
    )

    object Weapons : MenuButtons(
        title = "Weapons",
        logoPath = "icons/icon_weapons.png",
        navigationTarget = NavigationTargets.Weapons
    )

    object Competitive : MenuButtons(
        title = "Competitive",
        logoPath = "icons/icon_competitive.png",
        navigationTarget = NavigationTargets.Competitive
    )

    object Wingman : MenuButtons(
        title = "Wingman",
        logoPath = "icons/icon_wingman.png",
        navigationTarget = NavigationTargets.Wingman
    )

    object DangerZone : MenuButtons(
        title = "Danger Zone",
        logoPath = "icons/icon_danger_zone.png",
        navigationTarget = NavigationTargets.DangerZone
    )

    object ProfileRank : MenuButtons(
        title = "Profile Rank",
        logoPath = "icons/icon_profile_rank.png",
        navigationTarget = NavigationTargets.ProfileRank
    )
}

@Composable
fun MainMenu(router: Router) {
    var stateButtons: MenuButtons by remember { mutableStateOf(MenuButtons.Maps) }
    Box(
        modifier = Modifier.fillMaxHeight().preferredWidth(180.dp),
    ) {

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = dark,
            elevation = 6.dp
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Logo()
                ScrollableColumn(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    menuButtons.map {
                        MenuButton(
                            isActive = stateButtons == it,
                            title = it.title,
                            logoPath = it.logoPath
                        ) {
                            stateButtons = it
                            router.navigateTo(it.navigationTarget, true)
                        }
                    }
                }
            }
        }
    }
}