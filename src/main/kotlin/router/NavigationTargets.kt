package router

sealed class NavigationTargets {
    object Maps : NavigationTargets()
    object MapPoints : NavigationTargets()
    object Weapons : NavigationTargets()
    object Competitive : NavigationTargets()
    object Wingman : NavigationTargets()
    object DangerZone : NavigationTargets()
    object ProfileRank : NavigationTargets()
}