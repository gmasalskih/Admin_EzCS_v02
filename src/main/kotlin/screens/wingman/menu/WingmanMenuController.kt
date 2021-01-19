package screens.wingman.menu

import androidx.compose.runtime.*
import data.entitys.Wingman
import data.types.EntityType
import router.NavigationTargets
import screens.BaseMenuController
import screens.ViewState

class WingmanMenuController : BaseMenuController<WingmanMenuItemViewState>() {

    override val defaultItemViewState: WingmanMenuItemViewState = WingmanMenuItemViewState()

    override var viewState: ViewState<WingmanMenuItemViewState> by mutableStateOf(
        ViewState(
            title = "Wingman",
            item = defaultItemViewState
        )
    )

    fun navigateToWingmanAdd() {
        router.navigateTo(NavigationTargets.WingmanAdd)
    }

    fun navigateToWingmanEdit(id: String) {
        router.navigateTo(NavigationTargets.WingmanEdit(id))
    }

    override suspend fun setEntity() {
        setItemViewState(
            WingmanMenuItemViewState(
                service.getListEntitiesAsync(EntityType.WINGMAN.name, Wingman::class).await().sortedBy { it.order }
            )
        )
    }
}