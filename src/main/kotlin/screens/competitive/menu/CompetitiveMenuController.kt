package screens.competitive.menu

import androidx.compose.runtime.*
import data.entitys.Competitive
import data.types.ContentSourceType
import data.types.EntityType
import router.NavigationTargets
import screens.BaseMenuController
import screens.ViewState

class CompetitiveMenuController : BaseMenuController<List<CompetitiveMenuState>>() {
    override var state: ViewState<List<CompetitiveMenuState>> by mutableStateOf(
        ViewState(
            title = "Competitive",
            item = listOf()
        )
    )

    override fun onClear() {
        setItemState(listOf())
    }

    override suspend fun setEntities() {
        setItemState(
            service.getListEntities(EntityType.COMPETITIVE.name, Competitive::class).map { entity ->
                CompetitiveMenuState(
                    name = entity.name,
                    logo = ContentSourceType.ContentStorageThumbnail(entity.getDocumentName(), entity.logo),
                    documentName = entity.getDocumentName(),
                    order = entity.order,
                )
            }.sortedBy { it.order }
        )
    }

    fun navigateToCompetitiveEdit(documentName: String) {
        router.navigateTo(NavigationTargets.CompetitiveEdit(documentName))
    }

    fun navigateToCompetitiveAdd() {
        router.navigateTo(NavigationTargets.CompetitiveAdd)
    }
}