package screens.competitive.menu

import androidx.compose.runtime.*
import router.NavigationTargets
import screens.BaseController

class CompetitiveMenuController : BaseController<CompetitiveMenuState>() {
    override var _state: CompetitiveMenuState by mutableStateOf(CompetitiveMenuState())

    fun navigateToEditCompetitiveRank(rankId: String) {
        router.navigateTo(NavigationTargets.CompetitiveEdit(id = rankId))
    }

    fun navigateToAddCompetitiveRank() {
        router.navigateTo(NavigationTargets.CompetitiveAdd)
    }
}