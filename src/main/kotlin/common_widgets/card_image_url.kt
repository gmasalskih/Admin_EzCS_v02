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
import androidx.compose.ui.unit.dp
import data.types.ContentSourceType
import ui.*

@Composable
fun CardImageUrl(
    label: String,
    modifier: Modifier = Modifier,
    pathToFile: ContentSourceType,
    progressIndicatorColor: Color = dark,
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
            Box(
                modifier = Modifier.fillMaxWidth().aspectRatio(1f).clickable(onClick = onClick)
            ) {
                ImageLoader.Image(
                    contentSourceType = pathToFile,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillWidth,
                    progressIndicatorColor = progressIndicatorColor
                )
                Text(
                    text = label,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(alignment = Alignment.BottomCenter),
                    fontSize = fontSize8sp,
                    fontFamily = verdanaRegular,
                    color = dark,
                )
            }
            Box(
                modifier = Modifier.weight(1f, true)
            ) {
                Text(
                    text = pathToFile.value,
                    textAlign = TextAlign.Center,
                    fontFamily = verdanaBold,
                    fontSize = fontSize8sp,
                    modifier = Modifier.align(Alignment.Center),
                    color = dark
                )
            }
        }
    }
}