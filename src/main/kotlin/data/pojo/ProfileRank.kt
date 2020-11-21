package data.pojo

import data.enums.FirestoreCollections
import data.enums.RankTypes


data class ProfileRank(
    val collection: FirestoreCollections = FirestoreCollections.RANKS,
    val contentsPath:String,
    var description: String,
    val id: String,
    var logo: String,
    val name: String,
    val rankType: RankTypes = RankTypes.PROFILE_RANK,
)