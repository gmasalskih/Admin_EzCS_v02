package data.entitys

import data.types.EntityType
import utils.ContentType
import utils.DataType

data class ProfileRank(
    @DataType
    override val name: String = "",

    @DataType
    override val entityType: EntityType = EntityType.PROFILE_RANK,

    @DataType
    var xp: String = "",

    @ContentType
    var logo: String = "",

    @DataType
    val order: Int = 0
) : Entity