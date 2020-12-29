package screens.test

import data.types.ContentSourceType
import screens.State

data class TestState(
    val content: ContentSourceType = ContentSourceType.Empty
) : State {
    override fun isValid(): Boolean = content !is ContentSourceType.Empty
}