package screens

import data.types.EntityType
import utils.toValidId

interface State {
    val name: String
    val entityType: EntityType
    fun isValid(): Boolean
    fun getContentsPath() = "${entityType.name}/${name.toValidId()}"
}