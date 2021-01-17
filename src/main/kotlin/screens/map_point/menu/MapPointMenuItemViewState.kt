package screens.map_point.menu

import data.entitys.MapPoint
import data.types.CompetitiveFilterType
import data.types.GrenadeFilterType
import data.types.TickrateFilterType
import screens.ItemViewState

data class MapPointMenuItemViewState(
    val mapPointName: String = "",
    val mapName: String = "",
    val grenadeFilterType: GrenadeFilterType = GrenadeFilterType.All,
    val tickrateFilterType: TickrateFilterType = TickrateFilterType.All,
    val competitiveFilterType: CompetitiveFilterType = CompetitiveFilterType.All,
    val listMapPoint: List<MapPoint> = listOf()
) : ItemViewState {
    override fun isValid(): Boolean = true
}