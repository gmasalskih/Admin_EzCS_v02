package data.entitys

import data.types.EntityType
import utils.toValidId

interface Entity {
    val name: String
    val entityType: EntityType
    fun getDocumentName() = "${entityType.name}/${name.toValidId()}"
    fun pripare(){
        this
    }
}

/*

.replace("((.+\\()|(\\)))".toRegex(), "")
                .split(",")
                .map { "${it.trim()}\n" }
                .map { it.replace("=", " = ") }
                .reduce { acc, s -> "$acc$s" }
 */