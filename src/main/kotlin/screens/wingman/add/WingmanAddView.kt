package screens.wingman.add

import androidx.compose.runtime.Composable
import org.koin.core.inject
import screens.BaseView

class WingmanAddView : BaseView<WingmanAddController>() {
    override val controller by inject<WingmanAddController>()
}