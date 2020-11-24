package screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import common_widgets.Screen
import utils.Controller
import org.koin.core.KoinComponent
import utils.ViewComponent


abstract class BaseView<BC : Controller> : KoinComponent {

    abstract val controller: BC
    var state: @Composable () -> Unit by mutableStateOf(@Composable {})

    @Composable
    fun render() {
        Screen(this) {
            state.invoke()
        }
    }

    protected fun setContent(content: @Composable () -> Unit) {
        state = content
    }

    open fun onViewCreate(){
        println("onViewCreate ${javaClass.simpleName}")
    }

    open fun onViewDestroy(){
        println("onViewDestroy ${javaClass.simpleName}")
    }
}
