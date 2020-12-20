package screens.competitive.edit

import screens.State

data class CompetitiveEditState(
    val logo: String = "",
) : State {
    override fun isValid(): Boolean = logo.isNotBlank()
}