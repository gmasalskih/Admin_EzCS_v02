package screens.wingman.menu

import androidx.compose.runtime.*
import data.entitys.Wingman
import data.types.EntityType
import router.NavigationTargets
import screens.BaseMenuController
import screens.ViewState

class WingmanMenuController : BaseMenuController<List<Wingman>>() {
    override var state: ViewState<List<Wingman>> by mutableStateOf(ViewState(title = "Wingman", item = listOf()))

    fun navigateToWingmanAdd() {
        router.navigateTo(NavigationTargets.WingmanAdd)
    }

    fun navigateToWingmanEdit(id: String) {
        router.navigateTo(NavigationTargets.WingmanEdit(id))
    }

    override fun onClear() {
        setItemState(listOf())
    }

    override suspend fun setEntities() {
        setItemState(service.getListEntities(EntityType.WINGMAN.name, Wingman::class).sortedBy { it.order })
    }
}