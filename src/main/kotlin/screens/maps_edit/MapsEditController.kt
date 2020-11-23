package screens.maps_edit

import androidx.compose.runtime.*
import screens.BaseController

class MapsEditController : BaseController<MapsEditViewState>() {
    override var _viewState: MapsEditViewState by mutableStateOf(MapsEditViewState())

    override fun clearViewState() {
        //TODO("Not yet implemented")
    }

    override fun onViewCreate() {
        _viewState = _viewState.copy(
            mapId = "dust2",
            mapName = "Dust II",
            pathToLogo = "https://avatarko.ru/img/kartinka/33/multfilm_lyagushka_32117.jpg",
            pathToMapImg = "https://avatarko.ru/img/kartinka/33/multfilm_lyagushka_32117.jpg",
            pathToWallpaper = "https://avatarko.ru/img/kartinka/33/multfilm_lyagushka_32117.jpg",
            isCompetitive = true
        )
    }
//"D:/EzCS/maps/dust2/wallpaper.png"
    override fun onViewDestroy() {
        _viewState = MapsEditViewState()
    }
}