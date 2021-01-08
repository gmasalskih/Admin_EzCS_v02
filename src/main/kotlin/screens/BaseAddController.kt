package screens

import kotlinx.coroutines.launch

abstract class BaseAddController<I : State> : BaseController<I>() {

    protected abstract suspend fun upload(stateItem: I)

    fun onSubmit() = launch {
        showLoading()
        val item = state.item
        try {
            if (!item.isValid()) throw Exception("The entity ${state.item} is not valid!")
            upload(item)
            onClear()
            showData()
        } catch (e: Exception) {
            showError(e)
        }
    }

    override fun onViewCreate() {
        super.onViewCreate()
        onClear()
    }
}