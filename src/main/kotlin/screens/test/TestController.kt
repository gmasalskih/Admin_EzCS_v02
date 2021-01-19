package screens.test

import androidx.compose.runtime.*
import data.entitys.blueprint_weapon.BlueprintWeapon
import kotlinx.coroutines.launch
import org.koin.core.component.inject
import providers.RealtimeDatabaseProvider
import providers.content_provider.ContentProviderImpl
import providers.data_provider.DataProviderImpl
import screens.BaseController
import screens.ViewState

class TestController : BaseController<TestItemViewState>() {

    override val defaultItemViewState: TestItemViewState = TestItemViewState()

    override var viewState: ViewState<TestItemViewState> by mutableStateOf(
        ViewState(
            title = "Test",
            item = defaultItemViewState
        )
    )
    private val dropbox by inject<ContentProviderImpl>()
    private val firestore by inject<DataProviderImpl>()
    private val db by inject<RealtimeDatabaseProvider>()

    fun test() {
        controllerScope.launch {
            db.getMapOfDocuments(BlueprintWeapon::class.java).forEach { t, u ->
                println("$t ${u.visuals.weaponType}")
            }
        }
    }
}