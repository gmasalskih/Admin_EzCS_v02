package screens.competitive.add

import androidx.compose.runtime.Composable
import org.koin.core.inject
import screens.BaseView

class CompetitiveAddView() : BaseView<CompetitiveAddController>() {
    override val controller by inject<CompetitiveAddController>()

    @Composable
    override fun render() = renderContent {

    }
}