package data.entitys

import data.types.EntityType

data class ProfileRank(
    override val name: String = "",
    override val entityType: EntityType = EntityType.PROFILE_RANK,
    var xp: String = "",
    var logo: String = "",
) : Entity