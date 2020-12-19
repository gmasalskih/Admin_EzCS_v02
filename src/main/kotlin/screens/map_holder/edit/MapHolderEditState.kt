package screens.map_holder.edit

import data.types.EntityType
import screens.State
import utils.ContentType
import utils.DataType

data class MapHolderEditState(
    @DataType
    override val name: String = "",

    @ContentType
    val logo: String = "",

    @ContentType
    val map: String = "",

    @ContentType
    val wallpaper: String = "",

    @DataType
    val isCompetitive: Boolean = false,

    override val entityType: EntityType = EntityType.MAP_HOLDER
) : State {
    override fun isValid() = name.isNotBlank() && logo.isNotBlank() && map.isNotBlank() && wallpaper.isNotBlank()
}