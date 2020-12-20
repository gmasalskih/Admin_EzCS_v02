package screens.danger_zone.edit

import screens.State

data class DangerZoneEditSate(
    val logo:String = ""
) :State {
    override fun isValid(): Boolean = logo.isNotEmpty()
}