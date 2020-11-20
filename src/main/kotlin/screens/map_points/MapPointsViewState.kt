package screens.map_points

import screens.TypeScreenState
import screens.ViewState

data class MapPointsViewState(
    override val typeScreenState: TypeScreenState = TypeScreenState.Data,
    override val title: String = "MapPoints",
    override val hasBackArrowButton: Boolean = true,
) : ViewState