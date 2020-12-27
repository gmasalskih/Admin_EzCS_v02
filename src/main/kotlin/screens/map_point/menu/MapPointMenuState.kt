package screens.map_point.menu

import data.entitys.MapHolder
import data.entitys.MapPoint
import data.types.GrenadeType
import data.types.TickrateType
import screens.State

data class MapPointMenuState(
    val mapPointName: String = "",
    val mapName: String = "",
    val grenadeType: GrenadeType? = null,
    val tickrateTypes: List<TickrateType> = listOf(),
    val isCompetitive: Boolean = false,
    val listMap: List<MapHolder> = listOf(),
    val listMapPoint: List<MapPoint> = listOf()
) : State {
    override fun isValid(): Boolean = true
}