package data.entitys

import data.types.EntityType
import utils.toValidId

interface Entity {
    val name: String
    val entityType: EntityType
    fun getContentsPath() = "${entityType.name}/${getId()}"
    fun getId() = name.toValidId()
}