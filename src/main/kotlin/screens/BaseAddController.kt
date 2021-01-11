package screens

import kotlinx.coroutines.launch

abstract class BaseAddController<I : State> : BaseController<I>() {

    protected abstract suspend fun upload(stateItem: I)

    protected fun launchUploadingEntityOnServer(stateItem: I) = launch {
        showLoading()
        if (!stateItem.isValid()) throw Exception("The entity $stateItem is not valid!")
        upload(stateItem)
        setDefaultState()
        showData()
    }

    override fun onViewCreate() {
        super.onViewCreate()
        setDefaultState()
    }
}