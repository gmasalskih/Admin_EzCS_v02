package common_widgets

import androidx.compose.ui.text.style.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import data.types.ContentSourceType
import ui.*

@Composable
fun CardImage(
    label: String = "",
    modifier: Modifier = Modifier,
    pathToFile: ContentSourceType,
    progressIndicatorColor: Color = dark,
    onClickDel: (() -> Unit)? = null,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.size(100.dp, 150.dp).clickable(onClick = onClick).then(modifier),
        shape = roundedCorner5dp,
        elevation = elevation6dp,
        backgroundColor = greyAccent
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.fillMaxWidth().weight(1f).padding(5.dp)
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = label,
                    textAlign = TextAlign.Center,
                    fontSize = fontSize8sp,
                    fontFamily = verdanaBold,
                    color = dark,
                )
                if (onClickDel != null) {
                    Icon(
                        modifier = Modifier.align(Alignment.TopEnd).size(20.dp).clickable(onClick = onClickDel),
                        bitmap = imageResource(Icons.Cross),
                        tint = dark
                    )
                }
            }
            Box(
                modifier = Modifier.aspectRatio(1f).fillMaxSize()
            ) {
                ImageLoader.Image(
                    content = pathToFile,
                    modifier = Modifier.aspectRatio(1f).fillMaxSize(),
                    contentScale = ContentScale.FillWidth,
                    progressIndicatorColor = progressIndicatorColor
                )
            }
            Box(
                modifier = Modifier.fillMaxWidth().weight(1f).padding(5.dp)
            ) {
                Text(
                    text = pathToFile.value.substringAfterLast("/"),
                    modifier = Modifier.align(Alignment.Center),
                    textAlign = TextAlign.Center,
                    fontFamily = verdanaBold,
                    fontSize = fontSize8sp,
                    color = dark
                )
            }
        }
    }
}