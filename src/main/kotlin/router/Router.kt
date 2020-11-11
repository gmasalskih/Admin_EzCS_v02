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

    fun addView(target: AppState, view: ViewComponent): Router {
        routerMap[target] = view
        return this
    }

    fun init() {
        if (isFirstTime) {
            isFirstTime = !isFirstTime
            render()
        }
    }


    fun navigateTo(target: AppState) {
        backStack.add(target)
        appState = target
    }

    fun back() {
        if (backStack.size > 1) backStack.removeLast()
        appState = backStack.last()
    }


    private fun render() {
        Window {
            MaterialTheme {
                routerMap[appState]?.render()
            }
        }
    }
}