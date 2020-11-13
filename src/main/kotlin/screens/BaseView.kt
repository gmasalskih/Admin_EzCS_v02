package screens

import androidx.compose.runtime.Composable
import helper.Controller
import org.koin.core.KoinComponent

abstract class BaseView<VS : ViewState, BC : Controller> : KoinComponent {

    abstract val controller: BC

    @Composable
    fun render() {
        when (val screenState = controller.screenState) {
            ScreenState.Loading -> loading()
            is ScreenState.Error -> error(screenState.err)
            is ScreenState.Data<*> -> {
                data(screenState.viewState as VS)
            }
        }
    }

    fun onDestroy() {
        println("onDestroy $this")
    }

    @Composable
    protected abstract fun loading()

    @Composable
    protected abstract fun error(e: Exception)

    @Composable
    protected abstract fun data(viewState: VS)
}