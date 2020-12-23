package screens

import kotlinx.coroutines.launch

abstract class BaseAddController<I : State> : BaseController<I>() {

    abstract fun onClear()
    abstract fun onNameChange(name: String)

    override fun initState() {
        onClear()
    }

    protected abstract suspend fun upload(item: I)

    fun onSubmit() = cs.launch {

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
        initState()
    }
}