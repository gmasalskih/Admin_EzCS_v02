package screens.test

import androidx.compose.runtime.*
import org.koin.core.inject
import providers.content_provider.DropboxProvider
import providers.data_provider.FirestoreProvider
import screens.BaseController
import screens.ViewState

class TestController : BaseController<TestState>() {
    override var state: ViewState<TestState> by mutableStateOf(ViewState(title = "Test", item = TestState()))
    private val dropbox by inject<DropboxProvider>()
    private val firestore by inject<FirestoreProvider>()

    override fun onViewCreate() {
        super.onViewCreate()

    }

    override fun onViewDestroy() {
        super.onViewDestroy()
    }

    override fun initState() {
//        TODO("Not yet implemented")
    }
}