package screens.map_holder.menu

import androidx.compose.runtime.*
import data.pojo.MapHolder
import router.NavigationTargets
import screens.BaseController
import screens.ViewState

class MapHolderMenuController : BaseController<List<MapHolder>>() {

    override var state: ViewState<List<MapHolder>> by mutableStateOf(
        ViewState(
            title = "Maps",
            item = listOf(
                MapHolder(
                    id = "dust2",
                    name = "Dust II",
                    logo = "https://firebasestorage.googleapis.com/v0/b/ez-cs-f7e97.appspot.com/o/MAPS%2Fdust2%2Flogo.png?alt=media&token=102190cb-5c9a-4c51-aefb-bcedf17f6ddb",
                    wallpaper = "https://firebasestorage.googleapis.com/v0/b/ez-cs-f7e97.appspot.com/o/MAPS%2Fdust2%2Fwallpaper.png?alt=media&token=4477240b-c7af-4baf-a7f6-5de44fa8c2e0"
                ),
                MapHolder(
                    id = "mirage",
                    name = "Mirage",
                    logo = "https://firebasestorage.googleapis.com/v0/b/ez-cs-f7e97.appspot.com/o/MAPS%2Fmirage%2Flogo.png?alt=media&token=fb05ab64-6970-40ff-8306-0da7c72ce836",
                    wallpaper = "https://firebasestorage.googleapis.com/v0/b/ez-cs-f7e97.appspot.com/o/MAPS%2Fmirage%2Fwallpaper.png?alt=media&token=3feffd60-9d20-44c4-8b0a-9cf162e1e5da"
                ),
                MapHolder(
                    id = "inferno",
                    name = "Inferno",
                    logo = "https://firebasestorage.googleapis.com/v0/b/ez-cs-f7e97.appspot.com/o/MAPS%2Finferno%2Flogo.png?alt=media&token=5885b076-ea2c-46c3-abb8-c7d63a18ce3f",
                    wallpaper = "https://firebasestorage.googleapis.com/v0/b/ez-cs-f7e97.appspot.com/o/MAPS%2Finferno%2Fwallpaper.png?alt=media&token=26cddd13-9ef4-4bda-b7b8-aa2fbeccf11c"
                ),
            )
        )
    )

    fun navigateToMapHolderAdd() {
        router.navigateTo(NavigationTargets.MapHolderAdd)
    }

    fun navigateToMapHolderEdit(mapId: String) {
        router.navigateTo(NavigationTargets.MapHolderEdit(mapId))
    }
}