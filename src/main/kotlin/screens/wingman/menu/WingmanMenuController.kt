package screens.wingman.menu

import androidx.compose.runtime.*
import data.entitys.Wingman
import router.NavigationTargets
import screens.BaseController
import screens.ViewState

class WingmanMenuController : BaseController<List<Wingman>>() {
    override var state:ViewState<List<Wingman>> by mutableStateOf(
        ViewState(
            title = "Wingman",
            item = listOf(
                Wingman(
                    name = "Silver I",
                    logo = ""
                ),
                Wingman(
                    name = "Silver II",
                    logo = ""
                ),
                Wingman(
                    name = "Silver III",
                    logo = ""
                ),
            )
        )
    )

    fun navigateToWingmanAdd() {
        router.navigateTo(NavigationTargets.WingmanAdd)
    }

    fun navigateToWingmanEdit(id: String) {
        router.navigateTo(NavigationTargets.WingmanEdit(id))
    }

    override fun initState() {
//        TODO("Not yet implemented")
    }
}