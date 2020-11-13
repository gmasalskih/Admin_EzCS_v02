package router

import androidx.compose.runtime.getValue
import androidx.compose.runtime.*
import helper.ViewComponent
import screens.main_menu.MainMenuView
import screens.maps.MapsView

class Router(entryPoint: Pair<Navigator, ViewComponent>) {
    var currentScreen: ViewComponent by mutableStateOf(entryPoint.second)
        private set
    private val backStack by mutableStateOf(mutableListOf<Navigator>(entryPoint.first))

    fun navigateTo(target: Navigator) {
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

    private fun initCurrentScreen(target: Navigator) {
        currentScreen.onDestroy()
        currentScreen = when (target) {
            is Navigator.MainMenu -> MainMenuView()
            is Navigator.Maps -> MapsView(target.param)
        }
    }
}