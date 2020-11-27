package screens.wingman.menu

import data.pojo.Wingman
import screens.TypeScreenState
import screens.ViewState

data class WingmanMenuState(
    override val typeScreenState: TypeScreenState = TypeScreenState.Data,
    override val title: String = "Wingman",


    val items: List<Wingman> = listOf(
        Wingman(
            id = "wingman_1",
            name = "Silver I",
            logo = "https://firebasestorage.googleapis.com/v0/b/ez-cs-f7e97.appspot.com/o/WINGMAN%2Fs1%2F01_s1.png?alt=media&token=bb517a0e-f0bb-426e-a2d7-daa8908f4ac3",
            contentsPath = "D:/EzCS/ranks/competitive/",
            description = "WINGMAN/s1",
        ),
        Wingman(
            id = "wingman_2",
            name = "Silver II",
            logo = "https://firebasestorage.googleapis.com/v0/b/ez-cs-f7e97.appspot.com/o/WINGMAN%2Fs2%2F02_s2.png?alt=media&token=5a462193-1386-4935-94ff-10d93bd02a38",
            contentsPath = "WINGMAN/s2",
            description = "",
        ),
        Wingman(
            id = "wingman_3",
            name = "Silver III",
            logo = "https://firebasestorage.googleapis.com/v0/b/ez-cs-f7e97.appspot.com/o/WINGMAN%2Fs3%2F03_s3.png?alt=media&token=6ddf0cbd-2172-4b8c-a449-9f0f5ca7e28e",
            contentsPath = "WINGMAN/s3",
            description = "",
        ),
    )
) : ViewState