package screens.test

import androidx.compose.runtime.*
import kotlinx.coroutines.launch
import org.koin.core.component.inject
import providers.RealtimeDatabaseProvider
import providers.content_provider.ContentProviderImpl
import providers.data_provider.DataProviderImpl
import providers.parser_provider.ParserItemsGameFileProviderImpl
import screens.BaseController
import screens.ViewState
import utils.PATH_TO_ITEMS_GAME

class TestController : BaseController<TestState>() {

    override val defaultItemState: TestState = TestState()

    override var state: ViewState<TestState> by mutableStateOf(
        ViewState(
            title = "Test",
            item = defaultItemState
        )
    )
    private val dropbox by inject<ContentProviderImpl>()
    private val firestore by inject<DataProviderImpl>()
    private val db by inject<RealtimeDatabaseProvider>()

    fun test() {
        controllerScope.launch {

        }
    }
}