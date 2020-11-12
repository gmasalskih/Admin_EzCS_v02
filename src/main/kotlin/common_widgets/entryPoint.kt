package common_widgets

import androidx.compose.desktop.Window
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.VectorAsset
import androidx.compose.ui.graphics.vector.VectorPainter
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import router.Router

fun EntryPoint() {
    val router = Router
    Window(
        size = IntSize(1280, 720),
        title = "Admin EzCS"
    ) {
        MaterialTheme {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(text = router.getCurrentScreenName()) },
                        navigationIcon = {
                            if (router.backStackSize > 1) {
                                IconButton(onClick = router::back) {
                                    Image(
                                        asset = imageResource("icons/back.png"),
                                    )
                                }
                            } else{
                                IconButton(onClick = {},enabled = false) {
                                    Image(
                                        asset = imageResource("icons/home.png"),
                                        alpha = 0.5f,
                                        modifier = Modifier.size(36.dp)
                                    )
                                }
                            }
                        },
                    )
                }
            ) {
                router.render()
            }
        }
    }

}