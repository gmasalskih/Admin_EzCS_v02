package router

sealed class Navigator {
    object MainMenu : Navigator()
    data class Maps(val param: String) : Navigator()
}