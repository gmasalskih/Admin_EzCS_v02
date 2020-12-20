package screens

import data.entitys.Entity
import kotlinx.coroutines.launch

abstract class BaseEditController<E : Entity, I : State> : BaseController<I>() {
    protected lateinit var documentName: String
    protected lateinit var entity: E
    protected abstract suspend fun setRowEntity()
    protected abstract suspend fun setEntity()
    protected abstract suspend fun update()

    override fun initState() {
        cs.launch {
            setRowEntity()
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
        try {
            update()
            showData()
            router.back()
        } catch (e: Exception) {
            showError(e)
        }
    }

    fun onDelete() = cs.launch {
        showLoading()
        service.delete(documentName)
        router.back()
    }
}