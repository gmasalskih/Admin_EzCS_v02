package screens

import org.koin.core.KoinComponent
import org.koin.core.inject
import router.Router

abstract class BaseController<VS : ViewState> : KoinComponent {
    protected abstract var _state: VS
    protected val router: Router by inject()

    fun getViewState() = _state
    fun setViewState(viewState: VS) {
        _state = viewState
    }

    fun isNavigableBack() = router.isNavigableBack()
    fun back() = router.back()

    open fun onViewCreate() {/* STUB */
    }

    open fun onViewDestroy() {/* STUB */
    }
}
