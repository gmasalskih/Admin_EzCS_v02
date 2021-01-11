package screens

import data.types.StateType
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import providers.ServiceProvider
import router.Router
import kotlin.coroutines.CoroutineContext

abstract class BaseController<I : State> : KoinComponent, CoroutineScope {

    protected abstract var state: ViewState<I>
    protected abstract val defaultItemState: I
    private var job: Job = Job()
    private val ceh = CoroutineExceptionHandler { _, throwable ->
        showError(throwable)
        job.cancel()
        job = Job()
    }
    protected val router by inject<Router>()
    protected val service by inject<ServiceProvider>()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + job + ceh

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
        job = Job()
    }

    open fun onViewDestroy() {
        job.cancel()
    }
}

