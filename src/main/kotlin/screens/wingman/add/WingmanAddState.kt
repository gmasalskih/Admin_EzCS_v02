package screens.wingman.add

import screens.State

data class WingmanAddState(
    val name: String = "",
    val logo: String = "",
    val order: String = "",
) : State {
    override fun isValid() = name.isNotBlank() && logo.isNotBlank() && (order.toIntOrNull() ?: -1) > 0
}