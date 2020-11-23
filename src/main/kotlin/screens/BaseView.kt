package screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.onActive
import androidx.compose.runtime.onDispose
import common_widgets.Screen
import utils.Controller
import org.koin.core.KoinComponent

abstract class BaseView<BC : Controller> : KoinComponent {

    abstract val controller: BC

    @Composable
    abstract fun render()

    @Composable
    protected fun renderContent(content: @Composable () -> Unit) {
        onActive {
            controller.onViewCreate()
            println("onActive ${this@BaseView.javaClass.simpleName}")
        }
        onDispose {
            controller.onViewDestroy()
            println("onDispose ${this@BaseView.javaClass.simpleName}")
        }
        Screen(this) {
            content()
        }
    }
}