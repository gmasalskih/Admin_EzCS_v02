package router

import androidx.compose.runtime.*
import screens.BaseView
import screens.competitive.add.CompetitiveAddView
import screens.competitive.edit.CompetitiveEditView
import screens.competitive.menu.CompetitiveMenuView
import screens.danger_zone.add.DangerZoneAddView
import screens.danger_zone.edit.DangerZoneEditView
import screens.danger_zone.menu.DangerZoneMenuView
import screens.map_point.add.MapPointAddView
import screens.map_point.edit.MapPointEditView
import screens.map_point.menu.MapPointMenuView
import screens.map_holder.menu.MapHolderMenuView
import screens.map_holder.add.MapHolderAddView
import screens.map_holder.edit.MapHolderEditView
import screens.profile_rank.add.ProfileRankAddView
import screens.profile_rank.edit.ProfileRankEditView
import screens.profile_rank.menu.ProfileRankMenuView
import screens.test.TestView
import screens.weapon.add.WeaponAddView
import screens.weapon.edit.WeaponEditView
import screens.weapon.menu.WeaponMenuView
import screens.wingman.add.WingmanAddView
import screens.wingman.edit.WingmanEditView
import screens.wingman.menu.WingmanMenuView

class Router(entryPoint: Pair<NavigationTargets, BaseView<*>>) {
    var currentScreen: BaseView<*> by mutableStateOf(entryPoint.second)
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
            is NavigationTargets.CompetitiveEdit -> CompetitiveEditView(target.documentName)
            is NavigationTargets.CompetitiveMenu -> CompetitiveMenuView()

            //DangerZone
            is NavigationTargets.DangerZoneAdd -> DangerZoneAddView()
            is NavigationTargets.DangerZoneEdit -> DangerZoneEditView(target.documentName)
            is NavigationTargets.DangerZoneMenu -> DangerZoneMenuView()

            //MapPoints
            is NavigationTargets.MapPointsAdd -> MapPointAddView()
            is NavigationTargets.MapPointsEdit -> MapPointEditView(target.documentName)
            is NavigationTargets.MapPointsMenu -> MapPointMenuView()

            //Maps
            is NavigationTargets.MapHolderAdd -> MapHolderAddView()
            is NavigationTargets.MapHolderEdit -> MapHolderEditView(target.documentName)
            is NavigationTargets.MapHolderMenu -> MapHolderMenuView()

            //ProfileRank
            is NavigationTargets.ProfileRankAdd -> ProfileRankAddView()
            is NavigationTargets.ProfileRankEdit -> ProfileRankEditView(target.documentName)
            is NavigationTargets.ProfileRankMenu -> ProfileRankMenuView()

            //Weapons
            is NavigationTargets.WeaponsAdd -> WeaponAddView()
            is NavigationTargets.WeaponsEdit -> WeaponEditView(target.documentName)
            is NavigationTargets.WeaponsMenu -> WeaponMenuView()

            //Wingman
            is NavigationTargets.WingmanAdd -> WingmanAddView()
            is NavigationTargets.WingmanEdit -> WingmanEditView(target.documentName)
            is NavigationTargets.WingmanMenu -> WingmanMenuView()

            //Test
            is NavigationTargets.Test -> TestView()
        }
        currentScreen.onViewCreate()
    }
}