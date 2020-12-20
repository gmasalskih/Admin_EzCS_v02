package screens.competitive.menu

import androidx.compose.runtime.*
import data.entitys.Competitive
import data.types.EntityType
import router.NavigationTargets
import screens.BaseMenuController
import screens.ViewState

class CompetitiveMenuController : BaseMenuController<List<Competitive>>() {
    override var state: ViewState<List<Competitive>> by mutableStateOf(
        ViewState(
            title = "Competitive",
            item = listOf()
        )
    )

    override fun onClear() {
        setItemState(listOf())
    }

    override suspend fun setEntities() {
        setItemState(service.retrieveEntities(EntityType.COMPETITIVE.name, Competitive::class).sortedBy { it.order })
    }

    fun navigateToCompetitiveEdit(documentName: String) {
        router.navigateTo(NavigationTargets.CompetitiveEdit(documentName))
    }

    fun navigateToCompetitiveAdd() {
        router.navigateTo(NavigationTargets.CompetitiveAdd)
    }
}