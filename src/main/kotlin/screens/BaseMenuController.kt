package screens

import kotlinx.coroutines.launch

abstract class BaseMenuController<I : ItemViewState> : BaseController<I>() {

    protected abstract suspend fun setEntity()

    override fun onViewCreate() {
        super.onViewCreate()
        showLoading()
        setDefaultState()
        controllerScope.launch {
            setEntity()
            showData()
        }
    }
}