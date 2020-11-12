package router

import androidx.compose.desktop.Window
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import helper.ViewComponent

object Router {
    private val routerMap = mutableMapOf<AppState, ViewComponent>()
    private val backStack = mutableListOf<AppState>()
    private var appState by mutableStateOf<AppState>(AppState.MainMenu)
    private var isFirstTime = true
    var backStackSize by mutableStateOf(backStack.size)
        private set

    fun addView(target: AppState, view: ViewComponent): Router {
        routerMap[target] = view
        return this
    }

    fun getCurrentScreenName() = appState::class.java.simpleName

    fun init() {
        if (backStack.isEmpty()) backStack.add(appState)
    }

    fun navigateTo(target: AppState) {
        backStack.add(target)
        appState = target
        backStackSize = backStack.size
        println("navigateTo pressed - $backStack")
    }

    fun back() {
        if (backStack.size > 1) {
            println("back pressed - $backStack")
            backStack.removeLast()
            appState = backStack.last()
            backStackSize = backStack.size
            println("back pressed - $backStack")
        }
    }

    @Composable
    fun render() {
        println("render - $backStack")
        routerMap[appState]?.render()
        println("render - $backStack")
    }
}