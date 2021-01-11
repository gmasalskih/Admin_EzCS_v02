package screens

import data.types.StateType
import kotlinx.coroutines.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import providers.ServiceProvider
import router.Router

abstract class BaseController<I : State> : KoinComponent {
    protected abstract var state: ViewState<I>
    protected abstract val defaultItemState: I
    protected val router by inject<Router>()
    protected val service by inject<ServiceProvider>()
    private var job: Job = SupervisorJob()
    private val ceh = CoroutineExceptionHandler { _, throwable -> showError(throwable) }
    protected val controllerScope: CoroutineScope = CoroutineScope(Dispatchers.Default + job + ceh)

    open fun setDefaultState() {
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

    fun showError(e: Throwable) {
        state = state.copy(stateType = StateType.Error(err = e))
    }

    open fun onViewCreate() {
        job = SupervisorJob()
    }

    open fun onViewDestroy() {
        job.cancel()
    }
}

