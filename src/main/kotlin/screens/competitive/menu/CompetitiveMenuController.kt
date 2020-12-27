package screens.competitive.menu

import androidx.compose.runtime.*
import data.entitys.Competitive
import data.types.EntityType
import router.NavigationTargets
import screens.BaseMenuController
import screens.ViewState

class CompetitiveMenuController : BaseMenuController<CompetitiveMenuState>() {

    override val defaultItemState: CompetitiveMenuState = CompetitiveMenuState()

    override var state: ViewState<CompetitiveMenuState> by mutableStateOf(
        ViewState(
            title = "Competitive",
            item = defaultItemState
        )
    )

    fun navigateToCompetitiveEdit(documentName: String) {
        router.navigateTo(NavigationTargets.CompetitiveEdit(documentName))
    }

    fun navigateToCompetitiveAdd() {
        router.navigateTo(NavigationTargets.CompetitiveAdd)
    }

    override suspend fun setEntity() {
        setItemState(
            CompetitiveMenuState(
                service.getListEntities(EntityType.COMPETITIVE.name, Competitive::class).sortedBy { it.order }
            )
        )
    }
}