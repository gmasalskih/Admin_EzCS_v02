package data.entitys

import data.types.EntityType
import utils.toValidId

interface Entity {
    val name: String
    val entityType: EntityType
    fun contentsPath() = "${entityType.name}/${id()}"
    fun id() = name.toValidId()
}