package screens.map_point.menu

import androidx.compose.runtime.*
import data.entitys.MapHolder
import data.entitys.MapPoint
import data.types.*
import router.NavigationTargets
import screens.BaseMenuController
import screens.ViewState
import utils.containsOnly

class MapPointMenuController : BaseMenuController<MapPointMenuItemViewState>() {

    override val defaultItemViewState: MapPointMenuItemViewState = MapPointMenuItemViewState()
    private lateinit var defaultListMapPoint: List<MapPoint>
    private lateinit var defaultListMapHolder: List<MapHolder>

    override var viewState: ViewState<MapPointMenuItemViewState> by mutableStateOf(
        ViewState(
            title = "Map point",
            item = defaultItemViewState
        )
    )

    override suspend fun setEntity() {
        defaultListMapPoint = service.getListEntitiesAsync(EntityType.MAP_POINT.name, MapPoint::class).await()
        defaultListMapHolder = service.getListEntitiesAsync(EntityType.MAP_HOLDER.name, MapHolder::class).await()
        setItemViewState(
            MapPointMenuItemViewState(
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
        setItemViewState(
            viewState.item.copy(
                mapPointName = mapPointName.toLowerCase()
            )
        )
        applyFilters()
    }

    fun onMapNameFilterChange(mapName: String) {
        setItemViewState(
            viewState.item.copy(
                mapName = mapName.toLowerCase()
            )
        )
        applyFilters()
    }

    fun onGrenadeFilterChange(grenadeFilterType: GrenadeFilterType) {
        setItemViewState(
            viewState.item.copy(
                grenadeFilterType = grenadeFilterType
            )
        )
        applyFilters()
    }

    fun onTickrateFilterChange(tickrateFilterType: TickrateFilterType) {
        setItemViewState(
            viewState.item.copy(
                tickrateFilterType = tickrateFilterType
            )
        )
        applyFilters()
    }

    fun onCompetitiveFilterChange(competitiveFilterType: CompetitiveFilterType) {
        setItemViewState(
            viewState.item.copy(
                competitiveFilterType = competitiveFilterType
            )
        )
        applyFilters()
    }

    fun onResetFilters() {
        setItemViewState(
            defaultItemViewState.copy(
                listMapPoint = defaultListMapPoint
            )
        )
    }

    private fun applyMapNameFilter(mapPoint: MapPoint): Boolean {
        if (viewState.item.mapName.isBlank()) return true
        return (getCorrespondingMapHolder(mapPoint)?.name ?: "Unknown").contains(viewState.item.mapName, true)
    }

    private fun applyMapPointNameFilter(mapPoint: MapPoint): Boolean {
        if (viewState.item.mapPointName.isBlank()) return true
        return mapPoint.name.contains(viewState.item.mapPointName, true)
    }

    private fun applyGrenadeFilter(mapPoint: MapPoint): Boolean = when (viewState.item.grenadeFilterType) {
        GrenadeFilterType.All -> true
        GrenadeFilterType.Smoke -> mapPoint.grenadeType === GrenadeType.SMOKE
        GrenadeFilterType.Molotov -> mapPoint.grenadeType === GrenadeType.MOLOTOV
        GrenadeFilterType.Flash -> mapPoint.grenadeType === GrenadeType.FLASH
    }

    private fun applyTickrateFilter(mapPoint: MapPoint): Boolean = when (viewState.item.tickrateFilterType) {
        TickrateFilterType.All -> true
        TickrateFilterType.Tickrate64 -> mapPoint.tickrateTypes.containsOnly(TickrateType.TICKRATE_64)
        TickrateFilterType.Tickrate128 -> mapPoint.tickrateTypes.containsOnly(TickrateType.TICKRATE_128)
    }

    private fun applyCompetitiveFilter(mapPoint: MapPoint): Boolean = when (viewState.item.competitiveFilterType) {
        CompetitiveFilterType.All -> true
        CompetitiveFilterType.Yes -> getCorrespondingMapHolder(mapPoint)?.isCompetitive ?: false
        CompetitiveFilterType.No -> !(getCorrespondingMapHolder(mapPoint)?.isCompetitive ?: true)
    }

    private fun applyFilters() {
        setItemViewState(
            viewState.item.copy(
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
        println("getCorrespondingMapHolder")
        return try {
            defaultListMapHolder.find { it.getDocumentName() == mapPoint.mapDocumentName }
                ?: throw Exception("Map point $mapPoint can't find corresponding map holder")
        } catch (e: Exception) {
            null
        }
    }
}