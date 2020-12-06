package screens.map_holder.edit

import utils.ContentType
import utils.DataType

data class MapHolderEditState(
    @DataType
    val name: String = "",

    @ContentType
    val logo: String = "",

    @ContentType
    val map: String = "",

    @ContentType
    val wallpaper: String = "",

    @DataType
    val isCompetitive: Boolean = false
) {
    fun isValid() = name.isNotBlank() && logo.isNotBlank() && map.isNotBlank() && wallpaper.isNotBlank()
}