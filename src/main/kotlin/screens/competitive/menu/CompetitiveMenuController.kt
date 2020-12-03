package screens.competitive.menu

import androidx.compose.runtime.*
import data.pojo.Competitive
import router.NavigationTargets
import screens.BaseController
import screens.ViewState

class CompetitiveMenuController : BaseController<List<Competitive>>() {
    override var state: ViewState<List<Competitive>> by mutableStateOf(
        ViewState(
            title = "Competitive",
            item = listOf(
                Competitive(
                    name = "Silver I",
                    logo = ""
                ),
                Competitive(
                    name = "Silver II",
                    logo = ""
                ),
                Competitive(
                    name = "Silver III",
                    logo = ""
                ),
            )
        )
    )

    fun navigateToCompetitiveEdit(rankId: String) {
        router.navigateTo(NavigationTargets.CompetitiveEdit(id = rankId))
    }

    fun navigateToCompetitiveAdd() {
        router.navigateTo(NavigationTargets.CompetitiveAdd)
    }
}