package screens.danger_zone.add

import androidx.compose.runtime.*
import data.entitys.DangerZone
import data.types.FileType
import org.koin.core.component.KoinApiExtension
import screens.BaseAddController
import screens.ViewState
import utils.fileChooser
import utils.toValidName
import utils.toValidOrder

class DangerZoneAddController : BaseAddController<DangerZoneAddState>() {

    override val defaultItemState: DangerZoneAddState = DangerZoneAddState()

    override var state: ViewState<DangerZoneAddState> by mutableStateOf(
        ViewState(
            title = "new competitive rank",
            item = DangerZoneAddState()
        )
    )

    fun onLogoAdd() {
        fileChooser("Select logo", FileType.PNG, state.item.logo) { newLogo ->
            setItemState(
                state.item.copy(
                    logo = newLogo
                )
            )
        }
    }

    fun onNameChange(name: String) {
        setItemState(
            state.item.copy(
                name = name.toValidName()
            )
        )
    }

    fun onOrderChange(order: String) {
        setItemState(
            state.item.copy(
                order = order.toValidOrder()
            )
        )
    }

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