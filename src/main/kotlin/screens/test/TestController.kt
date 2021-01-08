package screens.test

import androidx.compose.runtime.*
import data.types.FileType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.swing.Swing
import kotlinx.coroutines.withContext
import org.koin.core.component.inject
import providers.content_provider.ContentProviderImpl
import providers.data_provider.DataProviderImpl
import screens.BaseController
import screens.ViewState
import utils.fileChooser

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

    fun onVideoAdd() {
        fileChooser("Select content", FileType.PNG, state.item.content) { newItem ->
            setItemState(
                state.item.copy(
                    content = newItem
                )
            )
        }
    }
}