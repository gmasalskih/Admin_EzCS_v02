package screens.maps.edit

import androidx.compose.runtime.*
import screens.BaseController

class MapsEditController : BaseController<MapsEditState>() {
    override var _state: MapsEditState by mutableStateOf(MapsEditState())

    override fun onViewCreate() {
        _state = _state.copy(
            mapId = "dust2",
            mapName = "Dust II",
            pathToLogo = "https://avatarko.ru/img/kartinka/33/multfilm_lyagushka_32117.jpg",
            pathToMapImg = "https://avatarko.ru/img/kartinka/33/multfilm_lyagushka_32117.jpg",
            pathToWallpaper = "https://avatarko.ru/img/kartinka/33/multfilm_lyagushka_32117.jpg",
            isCompetitive = true
        )
    }
    override fun onViewDestroy() {
        _state = MapsEditState()
    }
}