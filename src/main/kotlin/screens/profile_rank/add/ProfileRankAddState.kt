package screens.profile_rank.add

import screens.State

data class ProfileRankAddState(
    val name: String = "",
    var xp: String = "",
    var logo: String = "",
    val order: String = "",
) : State {
    override fun isValid(): Boolean =
        name.isNotBlank() && xp.isNotBlank() && logo.isNotBlank() && (order.toIntOrNull() ?: -1) > 0
}