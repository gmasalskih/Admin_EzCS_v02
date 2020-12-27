package screens.danger_zone.add

import androidx.compose.runtime.*
import data.entitys.DangerZone
import data.types.ContentSourceType
import screens.BaseAddController
import screens.ViewState
import utils.fileChooser
import utils.toValidName
import utils.toValidOrder

class DangerZoneAddController : BaseAddController<DangerZoneAddState>() {

    override val defaultItemState: DangerZoneAddState = DangerZoneAddState()

    override var state: ViewState<DangerZoneAddState> by mutableStateOf(
        ViewState(
            title = "Add new competitive rank",
            item = DangerZoneAddState()
        )
    )

    fun onLogoAdd() {
        val newLogo = fileChooser("Select logo", "png") ?: return
        if (!state.item.logo.value.contains(newLogo))
            setItemState(state.item.copy(logo = ContentSourceType.File(newLogo)))
    }

    fun onNameChange(name: String) = setItemState(state.item.copy(name = name.toValidName()))

    fun onOrderChange(order: String) = setItemState(state.item.copy(order = order.toValidOrder()))

    override suspend fun upload(stateItem: DangerZoneAddState) {
        service.uploadEntity(
            DangerZone(
                name = stateItem.name,
                logo = stateItem.logo.value,
                order = stateItem.order
            )
        )
    }
}