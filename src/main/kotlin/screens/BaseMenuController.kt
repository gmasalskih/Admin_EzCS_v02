package screens

import kotlinx.coroutines.launch

abstract class BaseMenuController<I> : BaseController<I>() {

    protected abstract fun onClear()
    protected abstract suspend fun setEntities()

    override fun initState() {
        cs.launch {
            try {
                setEntities()
                showData()
            } catch (e: Exception) {
                showError(e)
            }
        }
    }

    override fun onViewCreate() {
        super.onViewCreate()
        showLoading()
        onClear()
        initState()
    }
}