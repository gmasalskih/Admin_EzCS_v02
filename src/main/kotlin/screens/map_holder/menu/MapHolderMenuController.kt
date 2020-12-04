package screens.map_holder.menu

import androidx.compose.runtime.*
import data.types.EntityType
import data.entitys.MapHolder
import kotlinx.coroutines.*
import org.koin.core.inject
import providers.dropbox.DropboxProvider
import providers.firebase.FirestoreProvider
import router.NavigationTargets
import screens.BaseController
import screens.ViewState

class MapHolderMenuController : BaseController<List<MapHolder>>() {
    private val firestoreProvider by inject<FirestoreProvider>()
    private val dropboxProvider by inject<DropboxProvider>()
    override var state: ViewState<List<MapHolder>> by mutableStateOf(ViewState(title = "Maps", item = listOf()))

    fun navigateToMapHolderAdd() {
        router.navigateTo(NavigationTargets.MapHolderAdd)
    }

    fun navigateToMapHolderEdit(mapId: String) {
        router.navigateTo(NavigationTargets.MapHolderEdit(mapId))
    }

    private fun initState() = cs.launch {
        try {
            val maps =
                firestoreProvider.getCollectionItems(EntityType.MAP_HOLDER.name, MapHolder::class.java)
                    .map { mapHolder ->
                        mapHolder.copy(
                            logo = dropboxProvider.getFileUrl(mapHolder.createContentsPath(), mapHolder.logo),
                            wallpaper = dropboxProvider.getFileUrl(mapHolder.createContentsPath(), mapHolder.wallpaper),
                        )
                    }.toList()
            setItemState(maps)
            showData()
        } catch (e: Exception) {
            showError(e)
        }
    }

    override fun onViewCreate() {
        super.onViewCreate()
        showLoading()
        initState()
    }

    override fun onViewDestroy() {
        super.onViewDestroy()
        setItemState(listOf())
    }
}