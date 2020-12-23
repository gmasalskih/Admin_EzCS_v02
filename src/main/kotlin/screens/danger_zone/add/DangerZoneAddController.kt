package screens.danger_zone.add

import androidx.compose.runtime.*
import data.entitys.DangerZone
import screens.BaseAddController
import screens.ViewState
import utils.fileChooser
import utils.toValidName
import utils.toValidOrder

class DangerZoneAddController : BaseAddController<DangerZoneAddState>() {
    override var state: ViewState<DangerZoneAddState> by mutableStateOf(
        ViewState(
            title = "Add new competitive rank",
            item = DangerZoneAddState()
        )
    )

    override fun onClear() {
        setItemState(item = DangerZoneAddState())
    }

    fun onLogoAdd() {
        val newLogo = fileChooser("Select logo", "png") ?: return
        if (!state.item.logo.contains(newLogo)) setItemState(state.item.copy(logo = newLogo))
    }

    override fun onNameChange(name: String) = setItemState(state.item.copy(name = name.toValidName()))

    fun onOrderChange(order: String) = setItemState(state.item.copy(order = order.toValidOrder()))

    override fun initState() {
        onClear()
    }

    override suspend fun upload(item: DangerZoneAddState) {
        service.uploadEntity(DangerZone(name = item.name, logo = item.logo, order = item.order.toIntOrNull() ?: -1))
    }
}