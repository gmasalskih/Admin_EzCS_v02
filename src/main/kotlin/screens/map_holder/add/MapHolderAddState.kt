package screens.map_holder.add

import data.types.EntityType
import screens.State
import utils.ContentType
import utils.DataType

data class MapHolderAddState(
    @DataType
    override val name: String = "",

    override val entityType: EntityType = EntityType.MAP_HOLDER,

    @ContentType
    val logo: String = "",

    @ContentType
    val map: String = "",

    @ContentType
    val wallpaper: String = "",

    @DataType
    val isCompetitive: Boolean = false,
) : State {
    override fun isValid() = name.isNotBlank() && logo.isNotBlank() && map.isNotBlank() && wallpaper.isNotBlank()
}