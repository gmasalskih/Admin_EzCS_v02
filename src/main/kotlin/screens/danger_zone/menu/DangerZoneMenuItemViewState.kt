package screens.danger_zone.menu

import data.entitys.DangerZone
import screens.ItemViewState

data class DangerZoneMenuItemViewState(
    val listDangerZone: List<DangerZone> = listOf()
) : ItemViewState {
    override fun isValid(): Boolean = true
}