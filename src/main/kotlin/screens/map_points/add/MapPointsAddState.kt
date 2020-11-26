package screens.map_points.add

import data.enums.GrenadeTypes
import data.enums.TickRate
import screens.TypeScreenState
import screens.ViewState

data class MapPointsAddState(
    override val typeScreenState: TypeScreenState = TypeScreenState.Data,
    override val title: String = "New map point",
    val mapId: String = "",
    val mapPointName: String = "",
    val grenadeType: GrenadeTypes = GrenadeTypes.SMOKE,
    val listTickRates: List<TickRate> = listOf(),
    val pathToPreviewStartImg: String = "",
    val pathToPreviewEndImg: String = "",
    val listVideos: List<String> = listOf(),
    val listImages: List<String> = listOf()
) : ViewState