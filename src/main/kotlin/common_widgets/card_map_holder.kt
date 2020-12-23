package common_widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import data.types.ContentSourceType
import ui.*

@Composable
fun CardMapHolder(
    modifier: Modifier = Modifier,
    background: ContentSourceType,
    logo: ContentSourceType,
    name: String,
    isCompetitive: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.size(100.dp, 150.dp).then(modifier).clickable(onClick = onClick),
        shape = roundedCorner5dp,
        border = BorderStroke(1.dp, orangeAccent),
        elevation = elevation6dp
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            ImageLoader.Image(
                modifier = Modifier.fillMaxSize(),
                contentSourceType = background,
                contentScale = ContentScale.FillHeight,
                colorFilter = ColorFilter(Color.Black.copy(alpha = 0.4f), BlendMode.SrcOver)
            )
            Box(
                modifier = Modifier.fillMaxWidth().aspectRatio(1f).align(Alignment.TopCenter)
            ) {
                ImageLoader.Image(
                    modifier = Modifier.size(60.dp).align(Alignment.Center),
                    contentSourceType = logo,
                )
                if (isCompetitive) {
                    Icon(
                        modifier = Modifier.size(10.dp).align(Alignment.TopEnd).offset((-5).dp, 5.dp),
                        bitmap = imageResource("icons/icon_competitive.png"),
                        tint = greyAccent
                    )
                }
            }
            Text(
                text = name.toUpperCase(),
                fontFamily = verdanaBold,
                fontSize = fontSize12sp,
                modifier = Modifier.align(Alignment.BottomCenter).offset(y = (-10).dp),
                color = greyAccent
            )
        }
    }
}