package router

sealed class NavigationTargets {
    object CompetitiveAdd : NavigationTargets()
    data class CompetitiveEdit(val documentName: String) : NavigationTargets()
    object CompetitiveMenu : NavigationTargets()

    object DangerZoneAdd : NavigationTargets()
    data class DangerZoneEdit(val documentName: String) : NavigationTargets()
    object DangerZoneMenu : NavigationTargets()

    object MapPointsAdd : NavigationTargets()
    data class MapPointsEdit(val documentName: String) : NavigationTargets()
    object MapPointsMenu : NavigationTargets()

    object MapHolderAdd : NavigationTargets()
    data class MapHolderEdit(val documentName: String) : NavigationTargets()
    object MapHolderMenu : NavigationTargets()

    object ProfileRankAdd : NavigationTargets()
    data class ProfileRankEdit(val documentName: String) : NavigationTargets()
    object ProfileRankMenu : NavigationTargets()

    object WeaponsAdd : NavigationTargets()
    data class WeaponsEdit(val documentName: String) : NavigationTargets()
    object WeaponsMenu : NavigationTargets()

    object WingmanAdd : NavigationTargets()
    data class WingmanEdit(val documentName: String) : NavigationTargets()
    object WingmanMenu : NavigationTargets()

    object Test : NavigationTargets()
}