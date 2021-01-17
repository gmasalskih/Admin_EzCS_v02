package screens

import data.entitys.Entity
import kotlinx.coroutines.launch

abstract class BaseEditController<E : Entity, I : ItemViewState> : BaseController<I>() {
    protected lateinit var documentName: String
    protected abstract suspend fun setEntity()

    protected abstract fun mapper(itemViewState: I): E

    override fun onViewCreate() {
        super.onViewCreate()
        launchSetInitState()
    }

    @JvmName("documentName")
    fun setDocumentName(documentName: String) {
        this.documentName = documentName
    }


    private fun launchSetInitState() = controllerScope.launch {
        setDefaultState()
        showLoading()
        setEntity()
        showData()
    }

    protected fun launchUpdatingEntityOnServer(itemViewState: I) = controllerScope.launch {
        showLoading()
        if (!itemViewState.isValid()) throw Exception("The entity $itemViewState is not valid!")
        service.updateEntity(mapper(itemViewState))
        router.back()
    }

    protected fun launchDeletingEntityOnServer() = controllerScope.launch {
        showLoading()
        service.deleteEntity(documentName)
        router.back()
    }
}