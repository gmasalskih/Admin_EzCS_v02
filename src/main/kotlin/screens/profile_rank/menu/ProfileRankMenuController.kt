package screens.profile_rank.menu

import androidx.compose.runtime.*
import data.entitys.ProfileRank
import data.types.ContentSourceType
import data.types.EntityType
import router.NavigationTargets
import screens.BaseMenuController
import screens.ViewState

class ProfileRankMenuController : BaseMenuController<List<ProfileRankMenuState>>() {
    override var state: ViewState<List<ProfileRankMenuState>> by mutableStateOf(
        ViewState(
            title = "Profile Rank", item = listOf()
        )
    )

    fun navigateToProfileRankAdd() {
        router.navigateTo(NavigationTargets.ProfileRankAdd)
    }

    fun navigateToProfileRankEdit(documentName: String) {
        router.navigateTo(NavigationTargets.ProfileRankEdit(documentName))
    }

    override fun onClear() {
        setItemState(listOf())
    }

    override suspend fun setEntities() {
        setItemState(
            service.getListEntities(EntityType.PROFILE_RANK.name, ProfileRank::class).map { entity ->
                ProfileRankMenuState(
                    name = entity.name,
                    documentName = entity.getDocumentName(),
                    xp = entity.xp,
                    logo = ContentSourceType.ContentStorageOriginal(entity.getDocumentName(), entity.logo),
                    order = entity.order
                )
            }.sortedBy { it.order }
        )
    }
}