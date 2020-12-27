package screens.map_point.menu

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import common_widgets.*
import org.koin.core.inject
import screens.BaseView
import ui.spacedBy20dp
import utils.toOrderString

class MapPointMenuView : BaseView<MapPointMenuController>() {
    override val controller by inject<MapPointMenuController>()

    @Composable
    override fun setContent(controller: MapPointMenuController) {

//        ScrollableColumn(
//            modifier = Modifier.fillMaxSize(),
//            verticalArrangement = spacedBy20dp
//        ) {
//            Row(
//                horizontalArrangement = spacedBy20dp
//            ) {
//                TextFieldApp(
//                    value = controller.getViewState().item.name,
//                    label = "Enter map name",
//                    onTextChanged = controller::onNameChange
//                )
//                TextFieldApp(
//                    value = controller.getViewState().item.order.toOrderString(),
//                    label = "Enter map point name",
//                    onTextChanged = controller::onOrderChange
//                )
//            }
//            Row(
//                horizontalArrangement = spacedBy20dp
//            ) {
//                RadioGroupGrenadeTypes(
//                    onTypeSelected = controller::onGrenadeTypeChange,
//                    grenadeTypeSelected = controller.getViewState().item.grenadeType
//                )
//                CheckboxGroupTickrateTypes(
//                    listTickrateTypes = controller.getViewState().item.tickrateTypes,
//                    onTickrateTypeClick = controller::onTickrateChange
//                )
//            }
//            ScrollableRowAdd(
//                items = controller.getViewState().item,
//                cardAdd = { CardAdd(label = "add map point", onClick = controller::navigateToMapPointsAdd) },
//                cardItem = { mapHolder ->
//                    CardMapPoint(
//                        background = mapHolder.wallpaper,
//                        logo = mapHolder.logo,
//                        name = mapHolder.name,
//                        isCompetitive = mapHolder.isCompetitive,
//                        onClick = { controller.navigateToMapPointsEdit(mapHolder.getDocumentName()) }
//                    )
//                }
//            )
//        }
    }
}