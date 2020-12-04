package screens

import data.types.StateType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import org.koin.core.KoinComponent
import org.koin.core.inject
import router.Router

abstract class BaseController<I> : KoinComponent {

    protected abstract var state: ViewState<I>
    protected val router: Router by inject()
    protected lateinit var cs: CoroutineScope
        private set

    fun getViewState() = state

    protected fun setItemState(item: I) {
        state = state.copy(item = item)
    }

    fun isNavigableBack() = router.isNavigableBack()

    fun back() = router.back()

    fun showLoading() {
        state = state.copy(stateType = StateType.Loading)
    }

    fun showData() {
        state = state.copy(stateType = StateType.Data)
    }

    fun showError(e: Exception) {
        state = state.copy(stateType = StateType.Error(err = e))
    }

    open fun onViewCreate() {
        cs = CoroutineScope(Dispatchers.Default)
    }

    open fun onViewDestroy() {
        cs.cancel()
    }
}
