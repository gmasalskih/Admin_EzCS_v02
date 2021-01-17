package screens.map_point.add

import data.types.ContentSourceType
import data.types.GrenadeType
import data.types.TickrateType
import screens.ItemViewState

data class MapPointAddItemViewState(
    val name: String = "",
    val mapDocumentName: String = "",
    val grenadeType: GrenadeType = GrenadeType.SMOKE,
    val tickrateTypes: List<TickrateType> = listOf(),
    val previewStart: ContentSourceType = ContentSourceType.Empty,
    val previewEnd: ContentSourceType = ContentSourceType.Empty,
    val contentImages: List<ContentSourceType> = listOf(),
    val contentVideos: List<ContentSourceType> = listOf(),
) : ItemViewState {
    override fun isValid(): Boolean = name.isNotBlank() &&
            mapDocumentName.isNotEmpty() &&
            tickrateTypes.isNotEmpty() &&
            previewStart !is ContentSourceType.Empty &&
            previewEnd !is ContentSourceType.Empty &&
            contentImages.isNotEmpty() &&
            contentVideos.isNotEmpty()
}