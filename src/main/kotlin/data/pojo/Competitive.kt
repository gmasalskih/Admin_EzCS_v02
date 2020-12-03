package data.pojo

import data.types.EntityType
import utils.toValidName

data class Competitive(
    override val name: String = "",
    override val entityType: EntityType = EntityType.COMPETITIVE,
    val logo: String = "",
) : Entity