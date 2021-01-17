package screens.danger_zone.edit

import androidx.compose.runtime.*
import data.entitys.DangerZone
import data.types.ContentSourceType
import data.types.FileType
import screens.BaseEditController
import screens.ViewState
import utils.fileChooser
import utils.toValidOrder

class DangerZoneEditController : BaseEditController<DangerZone, DangerZoneEditSate>() {

    override val defaultItemViewState: DangerZoneEditSate = DangerZoneEditSate()

    override var viewState: ViewState<DangerZoneEditSate> by mutableStateOf(
        ViewState(
            title = "Edit rank",
            item = defaultItemViewState
        )
    )

    override suspend fun setEntity() {
        service.getEntityAsync(documentName, DangerZone::class).await().let { entity ->
            viewState = viewState.copy(title = "Edit ${entity.name}")
            setItemViewState(
                DangerZoneEditSate(
                    name = entity.name,
                    logo = ContentSourceType.ContentStorageOriginal(entity.getDocumentName(), entity.logo),
                    order = entity.order,
                )
            )
        }
    }

    fun onOrderChange(order: String) {
        setItemViewState(
            viewState.item.copy(
                order = order.toValidOrder()
            )
        )
    }

    fun onLogoChange() {
        fileChooser("Select logo", FileType.PNG, viewState.item.logo) { newLogo ->
            setItemViewState(
                viewState.item.copy(
                    logo = newLogo
                )
            )
        }
    }

    fun onDelete() {
        launchDeletingEntityOnServer()
    }

    fun onSubmit() {
        launchUpdatingEntityOnServer(viewState.item)
    }

    override fun mapper(itemViewState: DangerZoneEditSate): DangerZone {
        return DangerZone(
            name = itemViewState.name,
            logo = itemViewState.logo.value,
            order = itemViewState.order
        )
    }
}