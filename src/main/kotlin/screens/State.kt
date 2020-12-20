package screens

import data.types.EntityType
import utils.toValidId

interface State {
    val entityType: EntityType
    fun isValid(): Boolean
}