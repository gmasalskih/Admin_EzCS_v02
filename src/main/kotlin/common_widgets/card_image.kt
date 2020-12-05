package common_widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import ui.*

@Composable
fun CardImage(
    modifier: Modifier = Modifier,
    pathToFile: String,
    progressIndicatorColor: Color = dark,
    onClickDel: (() -> Unit)? = null,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.size(100.dp, 150.dp).then(modifier),
        shape = roundedCorner5dp,
        elevation = elevation6dp,
        backgroundColor = greyAccent
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (onClickDel == null) Spacer(modifier = Modifier.weight(weight = 1f, fill = true))
            else Box(
                Modifier.weight(weight = 1f, fill = true).aspectRatio(1f).align(Alignment.End)
                    .clickable(onClick = onClickDel).padding(5.dp)
            ) {
                IconApp(
                    pathToIcon = "icons/icon_trash.png",
                    tint = dark
                )
            }
            Box(
                modifier = Modifier.fillMaxWidth().aspectRatio(1f).clickable(onClick = onClick)
            ) {
                when {
                    pathToFile.toLowerCase().contains("(\\.png\$)|(\\.gif\$)".toRegex()) -> {
                        ImageUrl(
                            url = pathToFile,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.FillWidth,
                            progressIndicatorColor = progressIndicatorColor
                        )
                    }
                    pathToFile.toLowerCase().contains("\\.mp4$".toRegex()) -> {
                        Icon(
                            modifier = Modifier.padding(10.dp).fillMaxSize(),
                            bitmap = imageResource("icons/icon_video.png"),
                            tint = dark
                        )
                    }
                    else -> {
                        Icon(
                            modifier = Modifier.padding(10.dp).fillMaxSize(),
                            bitmap = imageResource("icons/icon_err.png"),
                            tint = dark
                        )
                    }
                }
            }
            Box(
                modifier = Modifier.weight(1f, true)
            ) {
                Text(
                    text = pathToFile.split("/").last().toLowerCase(),
                    fontFamily = verdanaBold,
                    fontSize = fontSize8sp,
                    modifier = Modifier.align(Alignment.Center),
                    color = dark
                )
            }
        }
    }
}