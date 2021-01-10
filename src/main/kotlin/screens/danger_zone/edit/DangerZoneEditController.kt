package screens.danger_zone.edit

import androidx.compose.runtime.*
import data.entitys.DangerZone
import data.types.ContentSourceType
import data.types.FileType
import screens.BaseEditController
import screens.ViewState
import utils.fileChooser
import utils.toValidOrder

class DangerZoneEditController : BaseEditController<DangerZoneEditSate>() {

    override val defaultItemState: DangerZoneEditSate = DangerZoneEditSate()

    override var state: ViewState<DangerZoneEditSate> by mutableStateOf(
        ViewState(
            title = "Edit rank",
            item = defaultItemState
        )
    )

    override suspend fun setEntity() {
        service.getEntity(documentName, DangerZone::class).let { entity ->
            state = state.copy(title = "Edit ${entity.name}")
            setItemState(
                DangerZoneEditSate(
                    name = entity.name,
                    logo = ContentSourceType.ContentStorageOriginal(entity.getDocumentName(), entity.logo),
                    order = entity.order,
                )
            )
        }
    }

    fun onOrderChange(order: String) {
        setItemState(
            state.item.copy(
                order = order.toValidOrder()
            )
        )
    }

    fun onLogoChange() {
        fileChooser("Select logo", FileType.PNG, state.item.logo) { newLogo ->
            setItemState(
                state.item.copy(
                    logo = newLogo
                )
            )
        }
    }

    fun onDelete(){
        launchDeletingEntityOnServer()
    }

    fun onSubmit() {
        launchUpdatingEntityOnServer(state.item)
    }

    override suspend fun update(stateItem: DangerZoneEditSate) {
        service.updateEntity(
            DangerZone(
                name = stateItem.name,
                logo = stateItem.logo.value,
                order = stateItem.order
            )
        )
    }
}