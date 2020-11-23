package screens.competitive.menu

import androidx.compose.runtime.Composable
import org.koin.core.inject
import screens.BaseView

class CompetitiveMenuView : BaseView<CompetitiveMenuController>() {
    override val controller by inject<CompetitiveMenuController>()

    @Composable
    override fun render() = renderContent {

    }
}