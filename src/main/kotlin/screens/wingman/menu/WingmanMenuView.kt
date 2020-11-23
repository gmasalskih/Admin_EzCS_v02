package screens.wingman.menu

import androidx.compose.runtime.Composable
import org.koin.core.inject
import screens.BaseView

class WingmanMenuView : BaseView<WingmanMenuController>() {
    override val controller by inject<WingmanMenuController>()

    @Composable
    override fun render() = renderContent {

    }
}