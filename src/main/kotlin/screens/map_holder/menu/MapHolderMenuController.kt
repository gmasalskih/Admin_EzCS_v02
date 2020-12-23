package screens.map_holder.menu

import androidx.compose.runtime.*
import data.types.EntityType
import data.entitys.MapHolder
import data.types.ContentSourceType
import router.NavigationTargets
import screens.BaseMenuController
import screens.ViewState

class MapHolderMenuController : BaseMenuController<List<MapHolderMenuState>>() {
    override var state: ViewState<List<MapHolderMenuState>> by mutableStateOf(
        ViewState(
            title = "Maps",
            item = listOf()
        )
    )

    fun navigateToMapHolderAdd() {
        router.navigateTo(NavigationTargets.MapHolderAdd)
    }

    fun navigateToMapHolderEdit(documentName: String) {
        router.navigateTo(NavigationTargets.MapHolderEdit(documentName))
    }

    override fun onClear() {
        setItemState(listOf())
    }

    override suspend fun setEntities() {
        setItemState(
            service.getListEntities(EntityType.MAP_HOLDER.name, MapHolder::class).map { mapHolder ->
                MapHolderMenuState(
                    documentName = mapHolder.getDocumentName(),
                    name = mapHolder.name,
                    isCompetitive = mapHolder.isCompetitive,
                    logo = ContentSourceType.DropBoxRaw(mapHolder.getDocumentName(), mapHolder.logo),
                    map = ContentSourceType.DropBoxThumbnail(mapHolder.getDocumentName(), mapHolder.map),
                    wallpaper = ContentSourceType.DropBoxThumbnail(mapHolder.getDocumentName(), mapHolder.wallpaper),
                )
            }
        )
    }
}