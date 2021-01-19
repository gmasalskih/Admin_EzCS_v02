package screens

import data.types.StateType
import kotlinx.coroutines.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import providers.ServiceProvider
import router.Router

abstract class BaseController<I : ItemViewState> : KoinComponent {
    abstract var viewState: ViewState<I>
        protected set
    protected abstract val defaultItemViewState: I
    protected val router by inject<Router>()
    protected val service by inject<ServiceProvider>()
    private var job: Job = SupervisorJob()
    private val ceh = CoroutineExceptionHandler { _, throwable -> showError(throwable) }
    protected val controllerScope: CoroutineScope = CoroutineScope(Dispatchers.Default + job + ceh)

    open fun setDefaultState() {
        setItemViewState(defaultItemViewState)
    }

    protected fun setItemViewState(itemViewState: I) {
        viewState = viewState.copy(item = itemViewState)
    }

    fun isNavigableBack() = router.isNavigableBack()

    fun back() = router.back()

    fun showLoading() {
        viewState = viewState.copy(stateType = StateType.Loading)
    }

    fun showData() {
        viewState = viewState.copy(stateType = StateType.Data)
    }

    fun showError(e: Throwable) {
        viewState = viewState.copy(stateType = StateType.Error(err = e))
    }

    open fun onViewCreate() {
        job = SupervisorJob()
    }

    open fun onViewDestroy() {
        job.cancel()
    }
}

