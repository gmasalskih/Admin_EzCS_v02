package screens.danger_zone.edit

import data.types.ContentSourceType
import screens.State

data class DangerZoneEditSate(
    val name: String = "",
    val logo: ContentSourceType = ContentSourceType.Empty,
    val order: Int = 0,
) : State {
    override fun isValid(): Boolean = name.isNotBlank() && logo !is ContentSourceType.Empty && order > 0
}