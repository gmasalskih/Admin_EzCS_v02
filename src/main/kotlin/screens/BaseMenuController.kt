package screens

abstract class BaseMenuController<I> : BaseController<I>() {

    override fun onViewCreate() {
        super.onViewCreate()
        showLoading()
        initState()
    }
}