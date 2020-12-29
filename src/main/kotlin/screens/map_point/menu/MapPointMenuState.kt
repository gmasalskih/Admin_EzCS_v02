package screens.map_point.menu

import data.entitys.MapPoint
import data.types.CompetitiveFilterType
import data.types.GrenadeFilterType
import data.types.TickrateFilterType
import screens.State

data class MapPointMenuState(
    val mapPointName: String = "",
    val mapName: String = "",
    val grenadeFilterType: GrenadeFilterType = GrenadeFilterType.All,
    val tickrateFilterType: TickrateFilterType = TickrateFilterType.All,
    val competitiveFilterType: CompetitiveFilterType = CompetitiveFilterType.All,
    val listMapPoint: List<MapPoint> = listOf()
) : State {
    override fun isValid(): Boolean = true
}