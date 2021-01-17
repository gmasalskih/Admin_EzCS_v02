package screens.danger_zone.add

import androidx.compose.runtime.*
import data.entitys.DangerZone
import data.types.FileType
import screens.BaseAddController
import screens.ViewState
import utils.fileChooser
import utils.toValidName
import utils.toValidOrder

class DangerZoneAddController : BaseAddController<DangerZone, DangerZoneAddItemViewState>() {

    override val defaultItemViewState: DangerZoneAddItemViewState = DangerZoneAddItemViewState()

    override var viewState: ViewState<DangerZoneAddItemViewState> by mutableStateOf(
        ViewState(
            title = "new competitive rank",
            item = DangerZoneAddItemViewState()
        )
    )

    fun onLogoAdd() {
        fileChooser("Select logo", FileType.PNG, viewState.item.logo) { newLogo ->
            setItemViewState(
                viewState.item.copy(
                    logo = newLogo
                )
            )
        }
    }

    fun onNameChange(name: String) {
        setItemViewState(
            viewState.item.copy(
                name = name.toValidName()
            )
        )
    }

    fun onOrderChange(order: String) {
        setItemViewState(
            viewState.item.copy(
                order = order.toValidOrder()
            )
        )
    }

    fun onClear() {
        setDefaultState()
    }

    fun onSubmit() {
        launchUploadingEntityOnServer(viewState.item)
    }

    override fun mapper(itemViewState: DangerZoneAddItemViewState): DangerZone {
        return DangerZone(
            name = itemViewState.name,
            logo = itemViewState.logo.value,
            order = itemViewState.order
        )
    }
}