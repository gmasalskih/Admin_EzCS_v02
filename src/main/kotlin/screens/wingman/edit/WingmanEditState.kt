package screens.wingman.edit

import screens.State

data class WingmanEditState(
    val logo: String = "",
) : State {
    override fun isValid(): Boolean = logo.isNotBlank()
}