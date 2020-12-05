package screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import common_widgets.Screen
import org.koin.core.KoinComponent

abstract class BaseView<C : BaseController<*>> : KoinComponent {

    abstract val controller: C

    @Composable
    fun render() {
        Screen(this) {
            Box(
                modifier = Modifier.fillMaxSize().padding(20.dp),
                content = { setContent(controller) }
            )
        }
    }

    @Composable
    protected abstract fun setContent(controller: C)

    open fun onViewCreate() {
        println("onViewCreate ${javaClass.simpleName}")
        controller.onViewCreate()
    }

    open fun onViewDestroy() {
        println("onViewDestroy ${javaClass.simpleName}")
        controller.onViewDestroy()
    }
}
