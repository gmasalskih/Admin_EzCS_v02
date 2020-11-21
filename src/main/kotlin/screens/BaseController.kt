package screens


import org.koin.core.KoinComponent
import org.koin.core.inject
import router.Router

abstract class BaseController<VS : ViewState> : KoinComponent {
    abstract var viewState: VS
    val router: Router by inject()
    abstract fun onViewDestroy()
}
