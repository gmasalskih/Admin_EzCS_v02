package screens.test

import screens.State

data class TestState(
    val name: String = "",
) : State {
    override fun isValid(): Boolean {
        return true
    }
}