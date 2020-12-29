package screens.test

import androidx.compose.runtime.*
import data.types.FileType
import org.koin.core.inject
import providers.content_provider.DropboxProvider
import providers.data_provider.FirestoreProvider
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
    private val dropbox by inject<DropboxProvider>()
    private val firestore by inject<FirestoreProvider>()

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