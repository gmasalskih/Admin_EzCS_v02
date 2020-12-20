package screens.competitive.add

import screens.State

data class CompetitiveAddState(
    val name: String = "",
    val logo: String = "",
    val order: String = "",
) : State {
    override fun isValid() = name.isNotBlank() && logo.isNotBlank() && (order.toIntOrNull() ?: -1) > 0
}