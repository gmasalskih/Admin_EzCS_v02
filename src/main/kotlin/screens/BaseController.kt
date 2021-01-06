package screens

import data.types.StateType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import org.koin.core.KoinComponent
import org.koin.core.inject
import providers.CoroutineProvider
import providers.Service
import router.Router

abstract class BaseController<I : State> : KoinComponent {

    protected abstract var state: ViewState<I>
    protected abstract val defaultItemState: I
    protected val router by inject<Router>()
    protected val service by inject<Service>()
    protected val cs by inject<CoroutineProvider>()
//    protected lateinit var cs: CoroutineScope
//        private set

    open fun onClear() {
        setItemState(defaultItemState)
    }

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
        cs.onStart()
    }

    open fun onViewDestroy() {
        cs.onStop()
    }
}

