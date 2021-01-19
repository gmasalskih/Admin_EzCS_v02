package screens.test

import data.types.ContentSourceType
import screens.ItemViewState

data class TestItemViewState(
    val content: ContentSourceType = ContentSourceType.Empty
) : ItemViewState {
    override fun isValid(): Boolean = content !is ContentSourceType.Empty
}