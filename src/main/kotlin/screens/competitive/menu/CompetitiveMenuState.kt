package screens.competitive.menu

import data.pojo.Competitive
import screens.TypeScreenState
import screens.ViewState

data class CompetitiveMenuState(
    override val typeScreenState: TypeScreenState = TypeScreenState.Data,
    override val title: String = "Competitive",
    val items: List<Competitive> = listOf(
        Competitive(
            id = "competitive_1",
            name = "Silver I",
            logo = "https://firebasestorage.googleapis.com/v0/b/ez-cs-f7e97.appspot.com/o/COMPETITIVE%2Fs1%2F01_s1.png?alt=media&token=52da7e86-7c8e-46ac-ab0d-867a74ab9a55",
            contentsPath = "D:/EzCS/ranks/competitive/",
            description = "COMPETITIVE/s1",
        ),
        Competitive(
            id = "competitive_2",
            name = "Silver II",
            logo = "https://firebasestorage.googleapis.com/v0/b/ez-cs-f7e97.appspot.com/o/COMPETITIVE%2Fs2%2F02_s2.png?alt=media&token=c1fa4821-9cd1-47e7-9ab9-6530a2b6ed79",
            contentsPath = "COMPETITIVE/s2",
            description = "",
        ),
        Competitive(
            id = "competitive_3",
            name = "Silver III",
            logo = "https://firebasestorage.googleapis.com/v0/b/ez-cs-f7e97.appspot.com/o/COMPETITIVE%2Fs3%2F03_s3.png?alt=media&token=7e2ec5fe-20b7-4907-99f9-1dba504899c8",
            contentsPath = "COMPETITIVE/s3",
            description = "",
        ),
    )
) : ViewState
