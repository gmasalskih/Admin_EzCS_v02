package screens.danger_zone.menu

import data.entitys.DangerZone
import screens.State

data class DangerZoneMenuState(
    val listDangerZone: List<DangerZone> = listOf()
) : State {
    override fun isValid(): Boolean = true
}