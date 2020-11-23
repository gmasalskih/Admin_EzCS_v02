package screens.wingman.edit

import androidx.compose.runtime.Composable
import org.koin.core.inject
import screens.BaseView

class WingmanEditView(val id: String) : BaseView<WingmanEditController>() {
    override val controller by inject<WingmanEditController>()

    @Composable
    override fun render() = renderContent {

    }
}