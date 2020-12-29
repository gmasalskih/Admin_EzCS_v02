package screens.map_point.edit

import data.types.ContentSourceType
import data.types.EntityType
import data.types.GrenadeType
import data.types.TickrateType
import screens.State

data class MapPointEditState(
    val name: String = "",
    val mapDocumentName: String = "",
    val grenadeType: GrenadeType = GrenadeType.SMOKE,
    val tickrateTypes: List<TickrateType> = listOf(),
    val previewStart: ContentSourceType = ContentSourceType.Empty,
    val previewEnd: ContentSourceType = ContentSourceType.Empty,
    val contentImages: List<ContentSourceType> = listOf(),
    val contentVideos: List<ContentSourceType> = listOf()
) : State {
    override fun isValid(): Boolean = name.isNotEmpty() &&
            mapDocumentName.isNotEmpty() &&
            tickrateTypes.isNotEmpty() &&
            previewStart !is ContentSourceType.Empty &&
            previewEnd !is ContentSourceType.Empty &&
            contentImages.isNotEmpty() &&
            contentVideos.isNotEmpty()
}
