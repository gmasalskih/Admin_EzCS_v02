package data.pojo

import data.enums.FirestoreCollections
import data.enums.RankTypes

data class DangerZone(
    val collection: FirestoreCollections = FirestoreCollections.RANKS,
    val contentsPath: String,
    val description: String,
    val id: String,
    val logo: String,
    val name: String,
    val rankType: RankTypes = RankTypes.DANGER_ZONE,
)