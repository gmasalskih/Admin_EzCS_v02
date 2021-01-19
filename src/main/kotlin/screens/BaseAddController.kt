package screens

import data.entitys.Entity
import kotlinx.coroutines.launch

abstract class BaseAddController<E : Entity, I : ItemViewState> : BaseController<I>() {

    protected abstract fun convertItemViewSateToEntity(itemViewState: I): E

    protected fun launchUploadingEntityOnServer(itemViewState: I) = controllerScope.launch {
        showLoading()
        if (!itemViewState.isValid()) throw Exception("The entity $itemViewState is not valid!")
        service.uploadEntity(convertItemViewSateToEntity(itemViewState))
        setDefaultState()
        showData()
    }

    override fun onViewCreate() {
        super.onViewCreate()
        setDefaultState()
    }
}