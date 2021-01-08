package screens

import kotlinx.coroutines.launch

abstract class BaseEditController<I : State> : BaseController<I>() {
    protected lateinit var documentName: String
    protected abstract suspend fun setEntity()
    protected abstract suspend fun update(stateItem: I)

    override fun onViewCreate() {
        super.onViewCreate()
        showLoading()
        launch {
            setEntity()
            showData()
        }
    }

    @JvmName("documentName")
    fun setDocumentName(documentName: String) {
        this.documentName = documentName
    }

    fun onSubmit() = launch {
        showLoading()
        val stateItem = state.item
        try {
            if (!stateItem.isValid()) throw Exception("The entity ${state.item} is not valid!")
            update(stateItem)
            router.back()
        } catch (e: Exception) {
            showError(e)
        }
    }


    fun onDelete(){
        launch {
            showLoading()
            service.deleteEntity(documentName)
            router.back()
        }
    }
}