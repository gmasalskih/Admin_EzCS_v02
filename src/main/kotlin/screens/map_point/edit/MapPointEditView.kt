package screens.map_point.edit

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import common_widgets.*
import org.koin.core.component.inject
import screens.BaseView
import ui.greyAccent
import ui.orangeAccent
import ui.spacedBy20dp

class MapPointEditView(documentName: String) : BaseView<MapPointEditController>() {
    override val controller by inject<MapPointEditController>()

    init {
        controller.setDocumentName(documentName)
    }

    @Composable
    override fun setContent(controller: MapPointEditController) {
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
                    CardImage(
                        onClick = controller::onPreviewStartChange,
                        pathToFile = controller.viewState.item.previewStart
                    )
                    CardImage(
                        onClick = controller::onPreviewEndChange,
                        pathToFile = controller.viewState.item.previewEnd
                    )
                }
                ScrollableAddRow(
                    modifier = Modifier.fillMaxWidth(),
                    items = controller.viewState.item.contentVideos,
                    cardAdd = {
                        CardAdd(
                            label = "Add video",
                            onClick = controller::onVideoAdd
                        )
                    },
                    cardItem = { fullPathToVideo ->
                        CardImage(
                            pathToFile = fullPathToVideo,
                            onClick = { controller.onVideoChange(fullPathToVideo) },
                            onClickDel = { controller.onVideoDelete(fullPathToVideo) }
                        )
                    }
                )
                ScrollableAddRow(
                    modifier = Modifier.fillMaxWidth(),
                    items = controller.viewState.item.contentImages,
                    cardAdd = {
                        CardAdd(
                            label = "Add image",
                            onClick = controller::onImageAdd
                        )
                    },
                    cardItem = { fullPathToImage ->
                        CardImage(
                            pathToFile = fullPathToImage,
                            onClick = { controller.onImageChange(fullPathToImage) },
                            onClickDel = { controller.onImageDelete(fullPathToImage) }
                        )
                    }
                )
            }
            Row(
                modifier = Modifier.align(Alignment.BottomEnd),
                horizontalArrangement = spacedBy20dp
            ) {
                ButtonApp(
                    label = "delete",
                    color = greyAccent,
                    onClick = controller::onDelete,
                )
                ButtonApp(
                    label = "submit",
                    isActive = controller.viewState.item.isValid(),
                    color = orangeAccent,
                    onClick = controller::onSubmit,
                )
            }
        }
    }
}