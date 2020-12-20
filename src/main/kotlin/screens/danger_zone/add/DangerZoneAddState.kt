package screens.danger_zone.add

import screens.State

data class DangerZoneAddState(
    val name: String = "",
    val logo: String = "",
    val order: String = "",
) : State {
    override fun isValid(): Boolean = name.isNotBlank() && logo.isNotBlank()
}