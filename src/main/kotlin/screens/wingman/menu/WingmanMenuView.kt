package screens.wingman.menu

import androidx.compose.runtime.Composable
import androidx.compose.runtime.onActive
import androidx.compose.runtime.onDispose
import org.koin.core.inject
import screens.BaseView

class WingmanMenuView : BaseView<WingmanMenuController>() {
    override val controller by inject<WingmanMenuController>()

    @Composable
    override fun setContent() {
//        TODO("Not yet implemented")
    }
}