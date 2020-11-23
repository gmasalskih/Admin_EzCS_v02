package screens.competitive.edit

import androidx.compose.runtime.Composable
import org.koin.core.inject
import screens.BaseView

class CompetitiveEditView(val id: String) : BaseView<CompetitiveEditController>() {
    override val controller by inject<CompetitiveEditController>()

    @Composable
    override fun render() = renderContent {

    }
}