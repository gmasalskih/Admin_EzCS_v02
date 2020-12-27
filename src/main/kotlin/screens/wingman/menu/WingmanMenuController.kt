package screens.wingman.menu

import androidx.compose.runtime.*
import data.entitys.Wingman
import data.types.EntityType
import router.NavigationTargets
import screens.BaseMenuController
import screens.ViewState

class WingmanMenuController : BaseMenuController<WingmanMenuState>() {

    override val defaultItemState: WingmanMenuState = WingmanMenuState()

    override var state: ViewState<WingmanMenuState> by mutableStateOf(
        ViewState(
            title = "Wingman",
            item = defaultItemState
        )
    )

    fun navigateToWingmanAdd() {
        router.navigateTo(NavigationTargets.WingmanAdd)
    }

    fun navigateToWingmanEdit(id: String) {
        router.navigateTo(NavigationTargets.WingmanEdit(id))
    }

    override suspend fun setEntity() {
        setItemState(
            WingmanMenuState(
                service.getListEntities(EntityType.WINGMAN.name, Wingman::class).sortedBy { it.order }
            )
        )
    }
}