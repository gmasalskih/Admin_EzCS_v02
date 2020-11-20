package screens.maps

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.VectorAsset
import androidx.compose.ui.res.imageResource
import org.koin.core.inject
import screens.BaseView
import ui.orangeAccent

class MapsView : BaseView<MapsViewState, MapsController>() {
    override val controller: MapsController by inject()
    @Composable
    override fun renderContent() {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = orangeAccent
        ) {

        }
    }
}