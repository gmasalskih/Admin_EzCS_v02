package screens

import androidx.compose.runtime.Composable
import common_widgets.Screen
import utils.Controller
import org.koin.core.KoinComponent

abstract class BaseView<VS : ViewState, BC : Controller> : KoinComponent {

    abstract val controller: BC

    @Composable
    fun render() {
        Screen(this) {
            renderContent()
        }
    }

    @Composable
    protected abstract fun renderContent()

    fun onDestroy() {
        println("onDestroy $this")
        controller.onViewDestroy()
    }
}