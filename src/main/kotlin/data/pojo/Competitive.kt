package data.pojo

import data.enums.FirestoreCollections
import data.enums.RankTypes

data class Competitive(
    val id: String,
    val name: String,
    val contentsPath: String,
    val logo: String,
    val description: String,
    val collection: FirestoreCollections = FirestoreCollections.RANKS,
    val rankType: RankTypes = RankTypes.COMPETITIVE,
)