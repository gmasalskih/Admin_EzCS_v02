package screens

import androidx.compose.runtime.*
import org.koin.core.KoinComponent
import org.koin.core.inject
import router.Router

abstract class BaseController<I> : KoinComponent {

    protected abstract var state: ViewState<I>
    protected val router: Router by inject()

    fun getViewState() = state
    fun setViewState(viewState: ViewState<I>) {
        state = viewState
    }

    fun setItemState(item: I) {
        setViewState(state.copy(item = item))
    }

    fun isNavigableBack() = router.isNavigableBack()
    fun back() = router.back()

    open fun onViewCreate() {/* STUB */
    }

    open fun onViewDestroy() {/* STUB */
    }
}
