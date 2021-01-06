package screens

import data.types.StateType
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf
import providers.CoroutineProvider
import providers.Service
import router.Router

@KoinApiExtension
abstract class BaseController<I : State> : KoinComponent {

    protected abstract var state: ViewState<I>
    protected abstract val defaultItemState: I
    protected val router by inject<Router>()
    protected val cs by inject<CoroutineProvider>()
    protected val service by inject<Service> { parametersOf(cs) }

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

