package screens.competitive.menu

import androidx.compose.runtime.*
import data.entitys.Competitive
import data.types.EntityType
import router.NavigationTargets
import screens.BaseMenuController
import screens.ViewState

class CompetitiveMenuController : BaseMenuController<CompetitiveMenuItemViewState>() {

    override val defaultItemViewState: CompetitiveMenuItemViewState = CompetitiveMenuItemViewState()

    override var viewState: ViewState<CompetitiveMenuItemViewState> by mutableStateOf(
        ViewState(
            title = "Competitive",
            item = defaultItemViewState
        )
    )

    fun navigateToCompetitiveEdit(documentName: String) {
        router.navigateTo(NavigationTargets.CompetitiveEdit(documentName))
    }

    fun navigateToCompetitiveAdd() {
        router.navigateTo(NavigationTargets.CompetitiveAdd)
    }

    override suspend fun setEntity() {
        setItemViewState(
            CompetitiveMenuItemViewState(
                service.getListEntitiesAsync(EntityType.COMPETITIVE.name, Competitive::class).await().sortedBy { it.order }
            )
        )
    }
}