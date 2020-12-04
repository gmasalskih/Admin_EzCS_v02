package screens.map_holder.add

data class MapHolderAddState(
    val name: String = "",
    val logo: String = "",
    val map: String = "",
    val wallpaper: String = "",
    val isCompetitive: Boolean = false
){
    fun isValid() = name.isNotBlank() && logo.isNotBlank() && map.isNotBlank() && wallpaper.isNotBlank()
}