package router

sealed class AppState {
    object MainMenu:AppState()
    object Maps:AppState()
}