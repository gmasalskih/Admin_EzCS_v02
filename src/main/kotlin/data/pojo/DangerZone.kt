package data.pojo

import data.enums.FirestoreCollections
import data.enums.RankTypes

data class DangerZone(
    val id: String,
    val name: String,
    val logo: String,
    val contentsPath: String,
    val description: String,
    val collection: FirestoreCollections = FirestoreCollections.RANKS,
    val rankType: RankTypes = RankTypes.DANGER_ZONE,
)