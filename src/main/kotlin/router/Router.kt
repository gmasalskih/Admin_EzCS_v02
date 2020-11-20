package router

import androidx.compose.runtime.getValue
import androidx.compose.runtime.*
import helper.ViewComponent
import screens.map_points.MapPointsView
import screens.maps.MapsView

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

    fun back() {
        if (backStack.size > 1) {
            backStack.removeLast()
            initCurrentScreen(backStack.last())
            println("back pressed - $backStack")
        }
    }

    private fun initCurrentScreen(target: NavigationTargets) {
        currentScreen.onDestroy()
        currentScreen = when (target) {
            NavigationTargets.Maps -> MapsView()
            NavigationTargets.MapPoints -> MapPointsView()
            NavigationTargets.Weapons -> MapsView()
            NavigationTargets.Competitive -> MapsView()
            NavigationTargets.Wingman -> MapsView()
            NavigationTargets.DangerZone -> MapsView()
            NavigationTargets.ProfileRank -> MapsView()
        }
    }
}