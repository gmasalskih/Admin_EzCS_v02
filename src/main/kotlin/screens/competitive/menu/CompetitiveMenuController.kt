package screens.competitive.menu

import androidx.compose.runtime.*
import data.entitys.Competitive
import data.types.EntityType
import kotlinx.coroutines.launch
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

    override fun initState() {
        setItemState(listOf())
        cs.launch {
            try {
                setItemState(
                    service.retrieveEntities(EntityType.COMPETITIVE.name, Competitive::class).sortedBy { it.order }
                )
                showData()
            } catch (e: Exception) {
                showError(e)
            }
        }
    }

    fun navigateToCompetitiveEdit(documentName: String) {
        router.navigateTo(NavigationTargets.CompetitiveEdit(documentName))
    }

    fun navigateToCompetitiveAdd() {
        router.navigateTo(NavigationTargets.CompetitiveAdd)
    }

}