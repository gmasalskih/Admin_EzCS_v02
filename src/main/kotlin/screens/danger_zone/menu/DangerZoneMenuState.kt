package screens.danger_zone.menu

import data.pojo.DangerZone
import screens.TypeScreenState
import screens.ViewState

data class DangerZoneMenuState(
    override val typeScreenState: TypeScreenState = TypeScreenState.Data,
    override val title: String = "Danger Zone",
    val items: List<DangerZone> = listOf(
        DangerZone(
            id = "lab_rat_i",
            name = "Lab rat i",
            logo = "https://firebasestorage.googleapis.com/v0/b/ez-cs-f7e97.appspot.com/o/DANGER_ZONE%2Flab_rat_i%2F01_lab_rat_i.png?alt=media&token=90268046-a81b-4e07-9493-cb18d9f9e4f5",
            contentsPath = "",
            description = "",
        ),
        DangerZone(
            id = "lab_rat_ii",
            name = "lab rat ii",
            logo = "https://firebasestorage.googleapis.com/v0/b/ez-cs-f7e97.appspot.com/o/DANGER_ZONE%2Flab_rat_ii%2F02_lab_rat_ii.png?alt=media&token=5c80478c-ff81-4169-875f-60e41d5966ff",
            contentsPath = "",
            description = "",
        ),
        DangerZone(
            id = "sprinting_hare_i",
            name = "sprinting hare i",
            logo = "https://firebasestorage.googleapis.com/v0/b/ez-cs-f7e97.appspot.com/o/DANGER_ZONE%2Fsprinting_hare_i%2F03_sprinting_hare_i.png?alt=media&token=5b36e5cf-ab28-4bd9-8cf8-800d02e297fd",
            contentsPath = "",
            description = "",
        ),
    )
) : ViewState

