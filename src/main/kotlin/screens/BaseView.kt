package screens

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import common_widgets.Screen
import utils.Controller
import org.koin.core.KoinComponent

abstract class BaseView<BC : Controller> : KoinComponent {

    abstract val controller: BC

    @Composable
    fun render() {
        Screen(this) {
            setContent()
        }
    }

    @Composable
    protected abstract fun setContent()

    open fun onViewCreate() {
        println("onViewCreate ${javaClass.simpleName}")
    }

    open fun onViewDestroy() {
        println("onViewDestroy ${javaClass.simpleName}")
    }
}
