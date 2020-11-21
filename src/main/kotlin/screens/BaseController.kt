package screens

import org.koin.core.KoinComponent
import org.koin.core.inject
import router.Router

abstract class BaseController<VS : ViewState> : KoinComponent {
    protected abstract var _viewState: VS
    protected val router: Router by inject()

    fun getViewState() = _viewState
    fun setViewState(viewState: VS) {
        _viewState = viewState
    }

    fun isNavigableBack() = router.isNavigableBack()
    fun back() = router.back()
    abstract fun clearViewState()

    abstract fun onViewCreate()
    abstract fun onViewDestroy()
}
