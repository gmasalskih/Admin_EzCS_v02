package screens

import androidx.compose.runtime.Composable
import common_widgets.Screen
import org.koin.core.KoinComponent

abstract class BaseView<C : BaseController<*>> : KoinComponent {

    abstract val controller: C

    @Composable
    fun render() {
        Screen(this) {
            setContent(controller)
        }
    }

    @Composable
    protected abstract fun setContent(controller: C)

    open fun onViewCreate() {
        println("onViewCreate ${javaClass.simpleName}")
    }

    open fun onViewDestroy() {
        println("onViewDestroy ${javaClass.simpleName}")
    }
}
