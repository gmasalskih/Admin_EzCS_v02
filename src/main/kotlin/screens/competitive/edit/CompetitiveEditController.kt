package screens.competitive.edit

import androidx.compose.runtime.*
import data.entitys.Competitive
import data.types.ContentSourceType
import data.types.FileType
import screens.BaseEditController
import screens.ViewState
import utils.fileChooser
import utils.toValidOrder

class CompetitiveEditController : BaseEditController<Competitive, CompetitiveEditItemViewState>() {

    override val defaultItemViewState: CompetitiveEditItemViewState = CompetitiveEditItemViewState()

    override var viewState: ViewState<CompetitiveEditItemViewState> by mutableStateOf(
        ViewState(
            title = "Edit rank",
            item = defaultItemViewState
        )
    )

    override suspend fun setEntity() {
        service.getEntityAsync(documentName, Competitive::class).await().let { competitive ->
            viewState = viewState.copy(title = "Edit ${competitive.name}")
            setItemViewState(
                convertEntityToItemViewSate(competitive)
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

    override fun convertItemViewSateToEntity(itemViewState: CompetitiveEditItemViewState): Competitive {
        return Competitive(
            name = itemViewState.name,
            logo = itemViewState.logo.value,
            order = itemViewState.order
        )
    }

    override fun convertEntityToItemViewSate(entity: Competitive): CompetitiveEditItemViewState {
        return CompetitiveEditItemViewState(
            name = entity.name,
            logo = ContentSourceType.ContentStorageThumbnail(entity.getDocumentName(), entity.logo),
            order = entity.order
        )
    }
}