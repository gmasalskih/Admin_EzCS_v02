package screens.map_point.menu

import androidx.compose.runtime.*
import data.entitys.MapHolder
import data.entitys.MapPoint
import data.types.*
import kotlinx.coroutines.launch
import router.NavigationTargets
import screens.BaseMenuController
import screens.ViewState
import utils.containsOnly

class MapPointMenuController : BaseMenuController<MapPointMenuState>() {

    override val defaultItemState: MapPointMenuState = MapPointMenuState()
    private lateinit var defaultListMapPoint: List<MapPoint>
    private lateinit var defaultListMapHolder: List<MapHolder>

    override var state: ViewState<MapPointMenuState> by mutableStateOf(
        ViewState(
            title = "Map point",
            item = defaultItemState
        )
    )

    override suspend fun setEntity() {
        defaultListMapPoint = service.getListEntities(EntityType.MAP_POINT.name, MapPoint::class)
        defaultListMapHolder = service.getListEntities(EntityType.MAP_HOLDER.name, MapHolder::class)
        setItemState(
            MapPointMenuState(
                listMapPoint = defaultListMapPoint
            )
        )
    }

    fun getMapName(mapPoint: MapPoint): String = getCorrespondingMapHolder(mapPoint)?.name ?: "Unknown"

    fun isMapCompetitive(mapPoint: MapPoint): Boolean = getCorrespondingMapHolder(mapPoint)?.isCompetitive ?: false

    fun navigateToMapPointsAdd() {
        router.navigateTo(NavigationTargets.MapPointsAdd)
    }

    fun navigateToMapPointsEdit(documentName: String) {
        router.navigateTo(NavigationTargets.MapPointsEdit(documentName))
    }

    fun onMapPointNameFilterChange(mapPointName: String) {
        setItemState(
            state.item.copy(
                mapPointName = mapPointName.toLowerCase()
            )
        )
        applyFilters()
    }

    fun onMapNameFilterChange(mapName: String) {
        setItemState(
            state.item.copy(
                mapName = mapName.toLowerCase()
            )
        )
        applyFilters()
    }

    fun onGrenadeFilterChange(grenadeFilterType: GrenadeFilterType) {
        setItemState(
            state.item.copy(
                grenadeFilterType = grenadeFilterType
            )
        )
        applyFilters()
    }

    fun onTickrateFilterChange(tickrateFilterType: TickrateFilterType) {
        setItemState(
            state.item.copy(
                tickrateFilterType = tickrateFilterType
            )
        )
        applyFilters()
    }

    fun onCompetitiveFilterChange(competitiveFilterType: CompetitiveFilterType) {
        setItemState(
            state.item.copy(
                competitiveFilterType = competitiveFilterType
            )
        )
        applyFilters()
    }

    fun onResetFilters() {
        setItemState(
            defaultItemState.copy(
                listMapPoint = defaultListMapPoint
            )
        )
    }

    private fun applyMapNameFilter(mapPoint: MapPoint): Boolean {
        if (state.item.mapName.isBlank()) return true
        return (getCorrespondingMapHolder(mapPoint)?.name ?: "Unknown").contains(state.item.mapName, true)
    }

    private fun applyMapPointNameFilter(mapPoint: MapPoint): Boolean {
        if (state.item.mapPointName.isBlank()) return true
        return mapPoint.name.contains(state.item.mapPointName, true)
    }

    private fun applyGrenadeFilter(mapPoint: MapPoint): Boolean = when (state.item.grenadeFilterType) {
        GrenadeFilterType.All -> true
        GrenadeFilterType.Smoke -> mapPoint.grenadeType === GrenadeType.SMOKE
        GrenadeFilterType.Molotov -> mapPoint.grenadeType === GrenadeType.MOLOTOV
        GrenadeFilterType.Flash -> mapPoint.grenadeType === GrenadeType.FLASH
    }

    private fun applyTickrateFilter(mapPoint: MapPoint): Boolean = when (state.item.tickrateFilterType) {
        TickrateFilterType.All -> true
        TickrateFilterType.Tickrate64 -> mapPoint.tickrateTypes.containsOnly(TickrateType.TICKRATE_64)
        TickrateFilterType.Tickrate128 -> mapPoint.tickrateTypes.containsOnly(TickrateType.TICKRATE_128)
    }

    private fun applyCompetitiveFilter(mapPoint: MapPoint): Boolean = when (state.item.competitiveFilterType) {
        CompetitiveFilterType.All -> true
        CompetitiveFilterType.Yes -> getCorrespondingMapHolder(mapPoint)?.isCompetitive ?: false
        CompetitiveFilterType.No -> !(getCorrespondingMapHolder(mapPoint)?.isCompetitive ?: true)
    }

    private fun applyFilters() {
        setItemState(
            state.item.copy(
                listMapPoint = defaultListMapPoint
                    //Entered map name
                    .filter { applyMapNameFilter(it) }
                    //Entered map point name
                    .filter { applyMapPointNameFilter(it) }
                    //Grenade filter
                    .filter { applyGrenadeFilter(it) }
                    //Tickrate filter
                    .filter { applyTickrateFilter(it) }
                    //Competitive filter
                    .filter { applyCompetitiveFilter(it) }
            )
        )
    }

    private fun getCorrespondingMapHolder(mapPoint: MapPoint): MapHolder? {
        return try {
            defaultListMapHolder.find { it.getDocumentName() == mapPoint.mapDocumentName }
                ?: throw Exception("Map point $mapPoint can't find corresponding map holder")
        } catch (e: Exception) {
            launch { showError(e) }
            null
        }
    }
}