package screens.test

import data.types.EntityType
import screens.State

data class TestState(
    override val name: String = "",
    override val entityType: EntityType = EntityType.MAP_HOLDER
) : State {
    override fun isValid(): Boolean {
        return true
    }
}