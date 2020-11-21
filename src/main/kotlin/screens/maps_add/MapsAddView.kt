package screens.maps_add

import androidx.compose.runtime.Composable
import org.koin.core.inject
import screens.BaseView
import utils.toValidId
import utils.toValidName

class MapsAddView : BaseView<MapsAddViewState, MapsAddController>() {
    override val controller: MapsAddController by inject()

    internal fun mapIdChange(mapId: String) {
        controller.viewState = controller.viewState.copy(
            mapId = mapId.toValidId()
        )
    }

    internal fun mapNameChange(mapName: String) {
        controller.viewState = controller.viewState.copy(
            mapName = mapName.toValidName()
        )
    }

    internal fun addLogoClick() {

    }

    internal fun addMapClick() {

    }

    internal fun addWallpaperClick() {
    }

    internal fun competitiveChecked(value: Boolean) {
        controller.viewState = controller.viewState.copy(
            isCompetitive = value
        )
    }

    internal fun clearBtnClick() {

    }

    internal fun submitBtnClick() {

    }

    @Composable
    override fun renderContent() {
        MapAddContent(this)
    }
}