package screens.map_holder.menu

import androidx.compose.runtime.*
import data.enums.FirestoreCollections
import data.pojo.MapHolder
import kotlinx.coroutines.*
import org.koin.core.inject
import providers.firebase.FirestoreProvider
import providers.firebase.StorageProvider
import router.NavigationTargets
import screens.BaseController
import screens.ViewState

class MapHolderMenuController : BaseController<List<MapHolder>>() {
    private var cs = CoroutineScope(Dispatchers.Main)
    private val firestoreProvider by inject<FirestoreProvider>()
    private val storageProvider by inject<StorageProvider>()
    override var state: ViewState<List<MapHolder>> by mutableStateOf(ViewState(title = "Maps", item = listOf()))

    fun navigateToMapHolderAdd() {
        router.navigateTo(NavigationTargets.MapHolderAdd)
    }

    fun navigateToMapHolderEdit(mapId: String) {
        router.navigateTo(NavigationTargets.MapHolderEdit(mapId))
    }

    private fun initState() = cs.launch {
        showLoading()
        val maps = withContext(Dispatchers.IO) {
            firestoreProvider.getCollectionItems(FirestoreCollections.MAPS.name, MapHolder::class.java)
                .map { mapHolder ->
                    mapHolder.copy(
                        logo = storageProvider.getFileUrl(mapHolder.getContentsPath(), mapHolder.logo),
                        wallpaper = storageProvider.getFileUrl(mapHolder.getContentsPath(), mapHolder.wallpaper),
                    )
                }.toList()
        }
        setItemState(maps)
        showData()
    }

    override fun onViewCreate() {
        super.onViewCreate()
        cs = CoroutineScope(Dispatchers.Main)
        initState()
    }

    override fun onViewDestroy() {
        super.onViewDestroy()
        setItemState(listOf())
        cs.cancel()
    }
}