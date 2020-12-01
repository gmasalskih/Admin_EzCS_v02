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
                    id = "competitive_1",
                    name = "Silver I",
                    logo = "https://firebasestorage.googleapis.com/v0/b/ez-cs-f7e97.appspot.com/o/COMPETITIVE%2Fs1%2F01_s1.png?alt=media&token=52da7e86-7c8e-46ac-ab0d-867a74ab9a55"
                ),
                Competitive(
                    id = "competitive_2",
                    name = "Silver II",
                    logo = "https://firebasestorage.googleapis.com/v0/b/ez-cs-f7e97.appspot.com/o/COMPETITIVE%2Fs2%2F02_s2.png?alt=media&token=c1fa4821-9cd1-47e7-9ab9-6530a2b6ed79"
                ),
                Competitive(
                    id = "competitive_3",
                    name = "Silver III",
                    logo = "https://firebasestorage.googleapis.com/v0/b/ez-cs-f7e97.appspot.com/o/COMPETITIVE%2Fs3%2F03_s3.png?alt=media&token=7e2ec5fe-20b7-4907-99f9-1dba504899c8"
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