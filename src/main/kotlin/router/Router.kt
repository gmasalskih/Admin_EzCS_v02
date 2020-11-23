package router

import androidx.compose.runtime.getValue
import androidx.compose.runtime.*
import utils.ViewComponent
import screens.map_points.MapPointsView
import screens.maps.MapsView
import screens.maps_add.MapsAddView
import screens.maps_edit.MapsEditView

class Router(entryPoint: Pair<NavigationTargets, ViewComponent>) {
    var currentScreen: ViewComponent by mutableStateOf(entryPoint.second)
        private set
    private val backStack by mutableStateOf(mutableListOf<NavigationTargets>(entryPoint.first))

    fun navigateTo(target: NavigationTargets, onTop: Boolean = false) {
        if (onTop) backStack.clear()
        initCurrentScreen(target)
        backStack.add(target)
        println("navigateTo - $target backStack - $backStack}")
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
        currentScreen = when (target) {
            is NavigationTargets.Maps -> MapsView()
            is NavigationTargets.MapsAdd -> MapsAddView()
            is NavigationTargets.MapsEdit -> MapsEditView(target.mapId)
            is NavigationTargets.MapPoints -> MapPointsView()
            is NavigationTargets.Weapons -> MapsView()
            is NavigationTargets.Competitive -> MapsView()
            is NavigationTargets.Wingman -> MapsView()
            is NavigationTargets.DangerZone -> MapsView()
            is NavigationTargets.ProfileRank -> MapsView()
        }
    }
}