package data.pojo

import data.types.EntityType

data class DangerZone(
    override val name: String = "",
    override val entityType: EntityType = EntityType.DANGER_ZONE,
    val logo: String = "",
) : Entity