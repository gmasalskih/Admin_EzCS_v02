package common_widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import data.types.ContentSourceType
import ui.*

@Composable
fun CardRank(
    name: String,
    pathToImage: ContentSourceType,
    modifier: Modifier = Modifier,
    progressIndicatorColor: Color = orangeAccent,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.size(100.dp, 150.dp).then(modifier).clickable(onClick = onClick),
        shape = roundedCorner5dp,
        border = BorderStroke(1.dp, orangeAccent),
        elevation = elevation6dp,
        backgroundColor = dark
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier.fillMaxWidth().aspectRatio(1f).align(Alignment.TopCenter).padding(10.dp)
            ) {
                ImageLoader.Image(
                    modifier = Modifier.align(Alignment.Center).fillMaxSize(),
                    content = pathToImage,
                    contentScale = ContentScale.FillWidth,
                    progressIndicatorColor = progressIndicatorColor
                )
            }
            Text(
                text = name.toUpperCase(),
                modifier = Modifier.align(Alignment.BottomCenter).padding(10.dp),
                textAlign = TextAlign.Center,
                fontFamily = verdanaBold,
                fontSize = fontSize8sp,
                color = greyAccent,
            )
        }
    }
}