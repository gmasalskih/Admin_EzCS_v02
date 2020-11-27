package screens.profile_rank.menu

import data.pojo.Wingman
import screens.TypeScreenState
import screens.ViewState

data class ProfileRankMenuState(
    override val typeScreenState: TypeScreenState = TypeScreenState.Data,
    override val title: String = "Profile Rank",
    val items: List<Wingman> = listOf(
        Wingman(
            id = "profile_rank_1",
            name = "Private Rank 1",
            logo = "https://firebasestorage.googleapis.com/v0/b/ez-cs-f7e97.appspot.com/o/PROFILE_RANK%2Fpr1%2F01.png?alt=media&token=2d13eaa2-b410-47cc-87bf-10fc9b7771ed",
            contentsPath = "D:/EzCS/ranks/competitive/",
            description = "PROFILE_RANK/pr1",
        ),
        Wingman(
            id = "profile_rank_2",
            name = "Private Rank 2",
            logo = "https://firebasestorage.googleapis.com/v0/b/ez-cs-f7e97.appspot.com/o/PROFILE_RANK%2Fpr2%2F02.png?alt=media&token=d757ffe8-a044-451a-bda9-71f992ceeb8b",
            contentsPath = "PROFILE_RANK/pr2",
            description = "",
        ),
        Wingman(
            id = "profile_rank_3",
            name = "Private Rank 3",
            logo = "https://firebasestorage.googleapis.com/v0/b/ez-cs-f7e97.appspot.com/o/PROFILE_RANK%2Fpr3%2F03.png?alt=media&token=3d03e94c-2195-45b6-a34c-840bef1f0f27",
            contentsPath = "PROFILE_RANK/pr3",
            description = "",
        ),
    )
) : ViewState