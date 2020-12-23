package screens

import kotlinx.coroutines.launch

abstract class BaseEditController<I : State> : BaseController<I>() {
    protected lateinit var documentName: String
    protected abstract suspend fun setEntity()
    protected abstract suspend fun update(stateItem: I)

    override fun initState() {
        cs.launch {
            setEntity()
            showData()
        }
    }

    override fun onViewCreate() {
        super.onViewCreate()
        showLoading()
        initState()
    }

    @JvmName("documentName")
    fun setDocumentName(documentName: String) {
        this.documentName = documentName
    }

    fun onSubmit() = cs.launch {
        showLoading()
        val stateItem = state.item
        try {
            if (!stateItem.isValid()) throw Exception("The entity ${state.item} is not valid!")
            update(stateItem)
            showData()
            router.back()
        } catch (e: Exception) {
            showError(e)
        }
    }

    fun onDelete() = cs.launch {
        showLoading()
        service.deleteEntity(documentName)
        router.back()
    }
}