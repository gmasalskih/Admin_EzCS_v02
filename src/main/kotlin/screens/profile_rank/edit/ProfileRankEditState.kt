package screens.profile_rank.edit

import data.types.EntityType

data class ProfileRankEditState(
    val name: String = "",
    val entityType: EntityType = EntityType.PROFILE_RANK,
    var xp: String = "",
    var logo: String = "",
)