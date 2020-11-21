package screens.maps

import screens.TypeScreenState
import screens.ViewState

data class MapsViewState(
    override val typeScreenState: TypeScreenState = TypeScreenState.Data,
    override val title: String = "Maps",
) : ViewState