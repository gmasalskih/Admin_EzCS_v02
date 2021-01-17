package screens.map_point.menu

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import common_widgets.*
import data.types.ContentSourceType
import org.koin.core.component.inject
import screens.BaseView
import ui.orangeAccent
import ui.spacedBy20dp
import ui.spacedBy40dp

class MapPointMenuView : BaseView<MapPointMenuController>() {
    override val controller by inject<MapPointMenuController>()

    @Composable
    override fun setContent(controller: MapPointMenuController) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            ScrollableColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = spacedBy20dp
            ) {
                Row(
                    horizontalArrangement = spacedBy20dp
                ) {
                    TextFieldApp(
                        value = controller.viewState.item.mapName,
                        label = "Enter map name",
                        onTextChanged = controller::onMapNameFilterChange
                    )
                    TextFieldApp(
                        value = controller.viewState.item.mapPointName,
                        label = "Enter map point name",
                        onTextChanged = controller::onMapPointNameFilterChange
                    )
                }
                Row(
                    horizontalArrangement = spacedBy40dp
                ) {
                    RadioGroupFilter(
                        label = "Grenade:",
                        filterSelected = controller.viewState.item.grenadeFilterType,
                        onFilterSelected = controller::onGrenadeFilterChange
                    )
                    RadioGroupFilter(
                        label = "Tickrate:",
                        filterSelected = controller.viewState.item.tickrateFilterType,
                        onFilterSelected = controller::onTickrateFilterChange
                    )
                    RadioGroupFilter(
                        label = "Competitive:",
                        filterSelected = controller.viewState.item.competitiveFilterType,
                        onFilterSelected = controller::onCompetitiveFilterChange
                    )
                }
                ScrollableAddRow(
                    items = controller.viewState.item.listMapPoint,
                    cardAdd = { CardAdd(label = "add map point", onClick = controller::navigateToMapPointsAdd) },
                    cardItem = { mapPoint ->
                        CardMapPoint(
                            mapPointName = mapPoint.name,
                            mapName = controller.getMapName(mapPoint),
                            previewStart = ContentSourceType.ContentStorageThumbnail(
                                mapPoint.getDocumentName(),
                                mapPoint.previewStart
                            ),
                            previewEnd = ContentSourceType.ContentStorageThumbnail(
                                mapPoint.getDocumentName(),
                                mapPoint.previewEnd
                            ),
                            tickrateTypes = mapPoint.tickrateTypes,
                            grenadeType = mapPoint.grenadeType,
                            isCompetitive = controller.isMapCompetitive(mapPoint),
                            onClick = { controller.navigateToMapPointsEdit(mapPoint.getDocumentName()) }
                        )
                    }
                )
            }
            ButtonApp(
                modifier = Modifier.align(Alignment.BottomEnd),
                label = "Reset",
                color = orangeAccent,
                onClick = controller::onResetFilters
            )
        }
    }
}