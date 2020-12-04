package screens.profile_rank.add

import data.types.EntityType

data class ProfileRankAddState(
    val name: String = "",
    val entityType: EntityType = EntityType.PROFILE_RANK,
    var xp: String = "",
    var logo: String = "",
)