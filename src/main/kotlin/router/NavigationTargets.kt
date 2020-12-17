package router

sealed class NavigationTargets {
    object CompetitiveAdd : NavigationTargets()
    data class CompetitiveEdit(val id: String) : NavigationTargets()
    object CompetitiveMenu : NavigationTargets()

    object DangerZoneAdd : NavigationTargets()
    data class DangerZoneEdit(val id: String) : NavigationTargets()
    object DangerZoneMenu : NavigationTargets()

    object MapPointsAdd : NavigationTargets()
    data class MapPointsEdit(val id: String) : NavigationTargets()
    object MapPointsMenu : NavigationTargets()

    object MapHolderAdd : NavigationTargets()
    data class MapHolderEdit(val id: String) : NavigationTargets()
    object MapHolderMenu : NavigationTargets()

    object ProfileRankAdd : NavigationTargets()
    data class ProfileRankEdit(val id: String) : NavigationTargets()
    object ProfileRankMenu : NavigationTargets()

    object WeaponsAdd : NavigationTargets()
    data class WeaponsEdit(val id: String) : NavigationTargets()
    object WeaponsMenu : NavigationTargets()

    object WingmanAdd : NavigationTargets()
    data class WingmanEdit(val id: String) : NavigationTargets()
    object WingmanMenu : NavigationTargets()

    object Test : NavigationTargets()
}