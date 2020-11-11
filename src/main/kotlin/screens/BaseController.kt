package screens


import org.koin.core.KoinComponent

abstract class BaseController<VS : ViewState> : KoinComponent {
    protected abstract var viewState: VS
    abstract var screenState: ScreenState

    protected fun setErr(e: Exception) {
        screenState = ScreenState.Error(e)
    }

    protected fun loading() {
        screenState = ScreenState.Loading
    }

    protected fun setNewViewState(viewState: VS) {
        screenState = ScreenState.Data(viewState)
    }
}