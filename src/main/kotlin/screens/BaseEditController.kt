package screens

import kotlinx.coroutines.launch

abstract class BaseEditController<I : State> : BaseController<I>() {
    protected lateinit var documentName: String
    protected abstract suspend fun setEntity()
    protected abstract suspend fun update(stateItem: I)

    override fun onViewCreate() {
        super.onViewCreate()
        launchSetInitState()
    }

    @JvmName("documentName")
    fun setDocumentName(documentName: String) {
        this.documentName = documentName
    }

    private fun launchSetInitState() = launch {
        setDefaultState()
        showLoading()
        setEntity()
        showData()
    }

    protected fun launchUpdatingEntityOnServer(stateItem: I) = launch {
        try {
            showLoading()
            if (!stateItem.isValid()) throw Exception("The entity $stateItem is not valid!")
            update(stateItem)
            router.back()
        } catch (e: Exception) {
            showError(e)
        }
    }

    protected fun launchDeletingEntityOnServer() = launch {
        showLoading()
        service.deleteEntity(documentName)
        router.back()
    }
}