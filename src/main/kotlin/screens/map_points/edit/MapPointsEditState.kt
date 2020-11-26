package screens.map_points.edit

import data.enums.GrenadeTypes
import data.enums.TickRate
import screens.TypeScreenState
import screens.ViewState

data class MapPointsEditState(
    override val typeScreenState: TypeScreenState = TypeScreenState.Data,
    override val title: String = "New map point",
    val mapId: String = "id_palas_to_under_palas",
    val mapPointName: String = "palas_to_under_palas",
    val grenadeType: GrenadeTypes = GrenadeTypes.SMOKE,
    val listTickRates: List<TickRate> = listOf(TickRate.TICKRATE_64),
    val pathToPreviewStartImg: String = "D:/EzCS/map_points/palas_to_under_palas_start.png",
    val pathToPreviewEndImg: String = "D:/EzCS/map_points/palas_to_under_palas_end.png",
    val listVideos: List<String> = listOf("C:/Users/gmasalskih/Videos/Counter-strike  Global Offensive/Counter-strike  Global Offensive 2020.10.28 - 14.06.08.01.mp4", "C:/Users/gmasalskih/Videos/Counter-strike  Global Offensive/Counter-strike  Global Offensive 2020.10.28 - 15.49.58.08.mp4"),
    val listImages: List<String> = listOf("D:/EzCS/map_points/palas_to_under_palas_start.png", "D:/EzCS/map_points/palas_to_under_palas_end.png")
) : ViewState