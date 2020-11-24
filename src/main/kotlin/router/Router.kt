package router

import androidx.compose.runtime.getValue
import androidx.compose.runtime.*
import screens.competitive.add.CompetitiveAddView
import screens.competitive.edit.CompetitiveEditView
import screens.competitive.menu.CompetitiveMenuView
import screens.danger_zone.add.DangerZoneAddView
import screens.danger_zone.edit.DangerZoneEditView
import screens.danger_zone.menu.DangerZoneMenuView
import screens.map_points.add.MapPointsAddView
import screens.map_points.edit.MapPointsEditView
import utils.ViewComponent
import screens.map_points.menu.MapPointsMenuView
import screens.maps.menu.MapsMenuView
import screens.maps.add.MapsAddView
import screens.maps.edit.MapsEditView
import screens.profile_rank.add.ProfileRankAddView
import screens.profile_rank.edit.ProfileRankEditView
import screens.profile_rank.menu.ProfileRankMenuView
import screens.weapons.add.WeaponsAddView
import screens.weapons.edit.WeaponsEditView
import screens.weapons.menu.WeaponsMenuView
import screens.wingman.add.WingmanAddView
import screens.wingman.edit.WingmanEditView
import screens.wingman.menu.WingmanMenuView

class Router(entryPoint: Pair<NavigationTargets, ViewComponent>) {
    var currentScreen: ViewComponent by mutableStateOf(entryPoint.second)
        private set
    private val backStack by mutableStateOf(mutableListOf<NavigationTargets>(entryPoint.first))

    init {
        entryPoint.second.onViewCreate()
    }

    fun navigateTo(target: NavigationTargets, onTop: Boolean = false) {
        if (target != backStack.last()) {
            if (onTop) backStack.clear()
            initCurrentScreen(target)
            backStack.add(target)
            println("navigateTo - $target backStack - $backStack}")
        }
    }

    fun isNavigableBack() = backStack.size > 1

    fun back() {
        if (backStack.size > 1) {
            backStack.removeLast()
            initCurrentScreen(backStack.last())
            println("back pressed - $backStack")
        }
    }

    private fun initCurrentScreen(target: NavigationTargets) {
        currentScreen.onViewDestroy()
        currentScreen = when (target) {
            //Competitive
            is NavigationTargets.CompetitiveAdd -> CompetitiveAddView()
            is NavigationTargets.CompetitiveEdit -> CompetitiveEditView(target.id)
            is NavigationTargets.CompetitiveMenu -> CompetitiveMenuView()

            //DangerZone
            is NavigationTargets.DangerZoneAdd -> DangerZoneAddView()
            is NavigationTargets.DangerZoneEdit -> DangerZoneEditView(target.id)
            is NavigationTargets.DangerZoneMenu -> DangerZoneMenuView()

            //MapPoints
            is NavigationTargets.MapPointsAdd -> MapPointsAddView()
            is NavigationTargets.MapPointsEdit -> MapPointsEditView(target.id)
            is NavigationTargets.MapPointsMenu -> MapPointsMenuView()

            //Maps
            is NavigationTargets.MapsAdd -> MapsAddView()
            is NavigationTargets.MapsEdit -> MapsEditView(target.id)
            is NavigationTargets.MapsMenu -> MapsMenuView()

            //ProfileRank
            is NavigationTargets.ProfileRankAdd -> ProfileRankAddView()
            is NavigationTargets.ProfileRankEdit -> ProfileRankEditView(target.id)
            is NavigationTargets.ProfileRankMenu -> ProfileRankMenuView()

            //Weapons
            is NavigationTargets.WeaponsAdd -> WeaponsAddView()
            is NavigationTargets.WeaponsEdit -> WeaponsEditView(target.id)
            is NavigationTargets.WeaponsMenu -> WeaponsMenuView()

            //Wingman
            is NavigationTargets.WingmanAdd -> WingmanAddView()
            is NavigationTargets.WingmanEdit -> WingmanEditView(target.id)
            is NavigationTargets.WingmanMenu -> WingmanMenuView()
        }
        currentScreen.onViewCreate()
    }
}